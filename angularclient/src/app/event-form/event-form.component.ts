import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {EventService} from "../service/event.service";
import {Event} from "../model/event";
import {MatDatepickerModule} from '@angular/material/datepicker';
//import {EventType} from "../model/eventType";


interface EventType {
  value: string;
  viewValue: string;


}
@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrl: './event-form.component.css'
})
export class EventFormComponent {
  event: Event;
  selectedEventType: string;
  eventTypes: EventType[] = [
    {value: 'WORK_EVENT', viewValue: 'Work event'},
    {value: 'TASK', viewValue: 'Task'},
    {value: 'SOCIAL_EVENT', viewValue: 'Social event'},
  ];

  eventType: any;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private eventService: EventService) {
    this.event = new Event();
  }

  onSubmit(){
    this.eventService.save(this.event).subscribe(result => this.gotoEventList());
  }

  gotoEventList(){
    this.router.navigate(['/events']);
  }
}
