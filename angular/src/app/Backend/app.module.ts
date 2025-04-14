import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from '../app-routing.module';
import { AppComponent } from '../app.component'; // Updated path to match the correct location of AppComponent
import { DashboardComponent } from './dashboard/dashboard.component'; // Corrected path to match the expected location


@NgModule({
  declarations: [
   
    DashboardComponent,
 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
