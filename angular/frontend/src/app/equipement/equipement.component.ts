import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Equipement } from './equipement.model';
import { formatDate } from '@angular/common';

import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import * as XLSX from 'xlsx';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'app-equipement',
  templateUrl: './equipement.component.html'
})
export class EquipementComponent implements OnInit {

  equipement: Equipement = {
    name: '',
    category: '',
    etat: '',
    dateAchat: '',
    marque: '',
    dateMaintenance: ''
  };

  equipements: Equipement[] = [];
  filteredEquipements: Equipement[] = [];

  editingId: number | null = null;
  showList = false;

  // 🔍 Filtres
  etatFilter = '';
  categoryFilter = '';

  // 📄 Pagination
  page = 1;
  pageSize = 5;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadEquipements();
  }

  toggleList() {
    this.showList = !this.showList;
    if (this.showList) {
      this.loadEquipements();
    }
  }

  loadEquipements() {
    this.http.get<Equipement[]>('http://localhost:9091/GestionEcole/api/equipments')
      .subscribe(data => {
        this.equipements = data;
        this.applyFilters();
      });
  }

  applyFilters() {
    this.filteredEquipements = this.equipements.filter(e =>
      (!this.etatFilter || e.etat === this.etatFilter) &&
      (!this.categoryFilter || e.category.toLowerCase().includes(this.categoryFilter.toLowerCase()))
    );
  }

  submit() {
    this.equipement.dateAchat = formatDate(this.equipement.dateAchat, 'yyyy-MM-dd', 'en-US');
    this.equipement.dateMaintenance = formatDate(this.equipement.dateMaintenance, 'yyyy-MM-dd', 'en-US');

    const url = this.editingId
      ? `http://localhost:9091/GestionEcole/api/equipments/${this.editingId}`
      : 'http://localhost:9091/GestionEcole/api/equipments';

    const request = this.editingId
      ? this.http.put(url, this.equipement)
      : this.http.post(url, this.equipement);

    request.subscribe({
      next: () => {
        alert(this.editingId ? 'Équipement modifié !' : 'Équipement ajouté !');
        this.loadEquipements();
        this.resetForm();
      },
      error: (err) => {
        console.error('Erreur backend :', err);
        alert("Erreur lors de l'enregistrement.");
      }
    });
  }

  editEquipement(e: Equipement) {
    this.equipement = { ...e };
    this.editingId = e.id!;
    this.showList = false;
  }

  deleteEquipement(id: number) {
    if (confirm("Confirmer la suppression ?")) {
      this.http.delete(`http://localhost:9091/GestionEcole/api/equipments/${id}`)
        .subscribe(() => this.loadEquipements());
    }
  }

  resetForm() {
    this.equipement = {
      name: '',
      category: '',
      etat: '',
      dateAchat: '',
      marque: '',
      dateMaintenance: ''
    };
    this.editingId = null;
  }

  exportPDF() {
    const doc = new jsPDF();
    autoTable(doc, {
      head: [['Nom', 'Catégorie', 'État', 'Marque', "Date d'achat", 'Date maintenance']],
      body: this.filteredEquipements.map(e => [
        e.name, e.category, e.etat, e.marque, e.dateAchat, e.dateMaintenance
      ])
    });
    doc.save('liste_equipements.pdf');
  }

  exportExcel() {
    const worksheet = XLSX.utils.json_to_sheet(this.filteredEquipements);
    const workbook = { Sheets: { 'Équipements': worksheet }, SheetNames: ['Équipements'] };
    const excelBuffer = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    const data: Blob = new Blob([excelBuffer], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8' });
    FileSaver.saveAs(data, 'liste_equipements.xlsx');
  }
}
