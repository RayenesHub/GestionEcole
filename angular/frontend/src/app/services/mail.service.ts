import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


export interface Mail {
  id?: number;
  sender: string;
  subject: string;
  body: string;
  date: Date;
}

@Injectable({
  providedIn: 'root'
})
export class MailService {

  private apiUrl = 'http://localhost:8080/mailing/mailing';

 constructor(private http: HttpClient) {}

 getAll(): Observable<Mail[]> {
  return this.http.get<Mail[]>(`${this.apiUrl}/show_mails`);
}

get(id: number): Observable<Mail> {
  return this.http.get<Mail>(`${this.apiUrl}/show_mail/${id}`);
}

create(mail: Mail): Observable<Mail> {
  return this.http.post<Mail>(`${this.apiUrl}/add_mail`, mail);
}

update(id: number, mail: Mail): Observable<Mail> {
  return this.http.put<Mail>(`${this.apiUrl}/mod_mail/${id}`, mail);
}

delete(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/del_mail/${id}`);
}

exportPdf(id: number): Observable<Blob> {
  return this.http.get(`${this.apiUrl}/${id}/export-pdf`, {
    responseType: 'blob'
  });
 
}

searchBySubject(subject: string): Observable<Mail[]> {
  return this.http.get<Mail[]>(`${this.apiUrl}/search?subject=${subject}`);
}

sendMail(mail: Mail): Observable<string> {
  return this.http.post(`${this.apiUrl}/add_and_send`, mail, { responseType: 'text' });
}
// sendSms(to: string, message: string): Observable<string> {
//   const params = new HttpParams()
//     .set('to', to)
//     .set('message', message);

//   return this.http.post(`${this.apiUrl}/send-sms`, null, {
//     params,
//     responseType: 'text' // Pour la réponse "SMS envoyé !" de Spring
//   });
// }


}