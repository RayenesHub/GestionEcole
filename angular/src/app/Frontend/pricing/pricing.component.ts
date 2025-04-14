import { Component } from '@angular/core';
import * as AOS from 'aos';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-pricing',
  templateUrl: './pricing.component.html',
  styleUrls: ['./pricing.component.css']
})
export class PricingComponent implements OnInit {
  ngOnInit(): void {
    AOS.init();
  }
}
