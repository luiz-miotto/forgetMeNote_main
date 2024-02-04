import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EventListComponent} from "./event-list/event-list.component";
import {EventFormComponent} from "./event-form/event-form.component";
import {UserListComponent} from "./user-list/user-list.component";

const routes: Routes = [
  {path: 'events', component: EventListComponent},
  {path: 'addEvent', component: EventFormComponent},
  {path: 'userList', component: UserListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
