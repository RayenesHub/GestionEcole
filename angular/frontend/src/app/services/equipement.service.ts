import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Equipement } from '../equipement/equipement.model';

@Injectable({
  providedIn: 'root'
})
export class EquipementService {
  private apiUrl = 'http://localhost:9091/GestionEcole/api/equipments'; // Assurez-vous que l'URL correspond à votre API

  constructor(private http: HttpClient) {}

  getAllEquipements(): Observable<Equipement[]> {
    return this.http.get<Equipement[]>(this.apiUrl);
  }

  addEquipement(equipement: Equipement): Observable<Equipement> {
    return this.http.post<Equipement>(this.apiUrl, equipement);
  }

  updateEquipement(id: number, equipement: Equipement): Observable<Equipement> {
    return this.http.put<Equipement>(`${this.apiUrl}/${id}`, equipement);
  }

  deleteEquipement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  getStatistiquesParEtat(): Observable<{ [key: string]: number }> {
    return this.http.get<{ [key: string]: number }>(`${this.apiUrl}/stats/etat`);
  }

}