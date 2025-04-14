import { Component, OnInit } from '@angular/core';
import { MailService, Mail } from '../services/mail.service';

@Component({
  selector: 'app-mail',
  templateUrl: './mail.component.html',
  styleUrls: ['./mail.component.css']
})
export class MailComponent implements OnInit{

  constructor(private mailService: MailService) {}
  editingMail: Mail | null = null;
  searchTerm: string = '';
  mails: Mail[] = [];
  to: string = '';
  message: string = '';
  responseMessage: string = '';
  newMail: Mail = {
    sender: '',
    subject: '',
    body: '',
    date: new Date()
  };

  ngOnInit(): void {
    this.loadMails();
  }

  loadMails() {
    this.mailService.getAll().subscribe(data => this.mails = data);
  }

  editMail(mail: Mail) {
    this.editingMail = { ...mail };
  }

  updateMail() {
    if (this.editingMail) {
      this.mailService.update(this.editingMail.id!, this.editingMail).subscribe(() => {
        this.editingMail = null;
        this.loadMails();
      });
    }
  }
 cancelEdit() {
    this.editingMail = null;
  }
  downloadPdf(id: number) {
    this.mailService.exportPdf(id).subscribe(blob => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = `mail_${id}.pdf`;
      a.click();
      window.URL.revokeObjectURL(url);
    });
  }

  
  addMail() {
    this.mailService.create(this.newMail).subscribe(() => {
      this.newMail = { sender: '', subject: '', body: '', date: new Date() };
      this.loadMails(); // rafraîchir la liste
    });
  }

  deleteMail(id: number) {
    if (confirm('Supprimer ce mail ?')) {
      this.mailService.delete(id).subscribe(() => this.loadMails());
    }
  }

  searchMails() {
    if (this.searchTerm.trim() === '') {
      this.loadMails();
    } else {
      this.mailService.searchBySubject(this.searchTerm).subscribe(data => this.mails = data);
    }
  }

  sendMail(mail: Mail) {
    this.mailService.sendMail(mail).subscribe({
      next: res => alert(res),
      error: err => alert('Erreur lors de l\'envoi : ' + err.message)
    });
  }
  // sendSms(): void {
  //   this.mailService.sendSms(this.to, this.message).subscribe({
  //     next: (response) => {
  //       this.responseMessage = response; // Affiche le message de succès
  //     },
  //     error: (err) => {
  //       this.responseMessage = 'Erreur lors de l\'envoi du SMS.';
  //     }
  //   });
  // }
  
}
