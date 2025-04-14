import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // ✅ AJOUTÉ

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './Frontend/home/home.component'; // Changed import path to match the correct structure

import { AboutComponent } from './Frontend/about/about.component';
import { TrainersComponent } from './Frontend/trainers/trainers.component';
import { EventsComponent } from './Frontend/events/events.component';
import { PricingComponent } from './Frontend/pricing/pricing.component';
import { ContactComponent } from './Frontend/contact/contact.component';
import { GetstartedComponent } from './Frontend/getstarted/getstarted.component';
import { CoursesComponent } from './Frontend/courses/courses.component';
import { DashboardComponent } from './Backend/dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    CoursesComponent,
    TrainersComponent,
    EventsComponent,
    PricingComponent,
    ContactComponent,
    GetstartedComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }