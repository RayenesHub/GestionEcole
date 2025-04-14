

import { Component, OnInit } from '@angular/core';
declare var AOS: any;

@Component({
  selector: 'app-trainers',
  templateUrl: './trainers.component.html',
  styleUrls: ['./trainers.component.css']
})
export class TrainersComponent implements OnInit {
  ngOnInit(): void {
    AOS.init();
  }
}
