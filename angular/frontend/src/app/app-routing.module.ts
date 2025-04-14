import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { CoursesComponent } from './courses/courses.component';
import { TrainersComponent } from './trainers/trainers.component';
import { EventsComponent } from './events/events.component';
import { PricingComponent } from './pricing/pricing.component';
import { ContactComponent } from './contact/contact.component';
import { GetstartedComponent } from './getstarted/getstarted.component';
import { EquipementComponent } from './equipement/equipement.component';
import { StatistiquesEquipementComponent } from './statistiques-equipement/statistiques-equipement.component';



const routes: Routes = [
  {path: '',component:HomeComponent},
  { path: 'about', component: AboutComponent },
  { path: 'equipements', component: EquipementComponent },
  { path: 'equipements/statistiques', component: StatistiquesEquipementComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'trainers', component: TrainersComponent },
  { path: 'events', component: EventsComponent },
  { path: 'pricing', component: PricingComponent },
  { path: 'contact', component: ContactComponent},
  { path: 'getstarted', component: GetstartedComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
