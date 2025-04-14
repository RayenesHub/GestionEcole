import { Component } from '@angular/core';
import * as AOS from 'aos';
@Component({
  selector: 'app-getstarted',
  templateUrl: './getstarted.component.html',
  styleUrls: ['./getstarted.component.css']
})
export class GetstartedComponent {
  ngAfterViewInit(): void {
    AOS.init();
  }
}
