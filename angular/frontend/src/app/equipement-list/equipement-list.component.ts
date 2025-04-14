import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Equipement } from '../equipement/equipement.model';




@Component({
  selector: 'app-equipement-list',
  templateUrl: './equipement-list.component.html'
})
export class EquipementListComponent {
  @Input() equipements: Equipement[] = [];
  @Output() edit = new EventEmitter<Equipement>();
  @Output() delete = new EventEmitter<number>();

  onEdit(e: Equipement) {
    this.edit.emit(e);
  }

  onDelete(id: number) {
    this.delete.emit(id);
  }
}
