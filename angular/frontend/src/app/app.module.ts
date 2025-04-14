import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; // ✅ AJOUTÉ
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { CoursesComponent } from './courses/courses.component';
import { TrainersComponent } from './trainers/trainers.component';
import { EventsComponent } from './events/events.component';
import { PricingComponent } from './pricing/pricing.component';
import { ContactComponent } from './contact/contact.component';
import { GetstartedComponent } from './getstarted/getstarted.component';

import { MailComponent } from './mail/mail.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { EquipementComponent } from './equipement/equipement.component';
import { FormsModule } from '@angular/forms';
import { EquipementListComponent } from './equipement-list/equipement-list.component';
import { StatistiquesEquipementComponent } from './statistiques-equipement/statistiques-equipement.component'; // Importer FormsModule
import { NgChartsModule } from 'ng2-charts';


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
MailingManagement
    MailComponent

    EquipementComponent,
    EquipementListComponent,
    StatistiquesEquipementComponent
main
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
 MailingManagement
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule

    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    NgChartsModule
 main
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

