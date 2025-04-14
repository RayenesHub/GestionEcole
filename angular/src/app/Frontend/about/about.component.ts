import { Component, OnInit } from '@angular/core';
import * as AOS from 'aos';


declare var PureCounter: any;

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
  ngOnInit(): void {
    AOS.init();
    new PureCounter(); // init counters
  }
}
