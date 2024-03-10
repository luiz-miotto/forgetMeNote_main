import { Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {EventFormComponent} from "./event-form/event-form.component";

export const routes: Routes = [

  {
    path:'',
    redirectTo: 'HomeComponent',
    title: 'Home Page',
    pathMatch: 'full'
  },
  {
    path: 'eventForm',
    component: EventFormComponent,
    title: 'Event Creation Form'
  },

];
