import { Component, AfterViewInit } from '@angular/core';
import * as AOS from 'aos';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements AfterViewInit {
  ngAfterViewInit(): void {
    AOS.init();
  }
}
