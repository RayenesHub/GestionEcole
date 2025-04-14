import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './Frontend/home/home.component';
import { AboutComponent } from './Frontend/about/about.component';
import { TrainersComponent } from './Frontend/trainers/trainers.component';
import { EventsComponent } from './Frontend/events/events.component';
import { PricingComponent } from './Frontend/pricing/pricing.component';
import { ContactComponent } from './Frontend/contact/contact.component';
import { GetstartedComponent } from './Frontend/getstarted/getstarted.component';
import { CoursesComponent } from './Frontend/courses/courses.component';
import { DashboardComponent } from './Backend/dashboard/dashboard.component';

const routes: Routes = [
  {path: '',component:HomeComponent},
  { path: 'about', component: AboutComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'trainers', component: TrainersComponent },
  { path: 'events', component: EventsComponent },
  { path: 'pricing', component: PricingComponent },
  { path: 'contact', component: ContactComponent},
  { path: 'getstarted', component: GetstartedComponent},
  {path: 'dashboard', component: DashboardComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
