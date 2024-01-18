import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {EventService} from "../service/event.service";
import {Event} from "../model/event";

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrl: './event-form.component.css'
})
export class EventFormComponent {
  event: Event;

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
