import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
}
export interface Equipement {
  id: number;
  name: string;
  quantity: number;
  category: string;
}

