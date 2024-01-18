import {Component, OnInit} from '@angular/core';
import {EventService} from "../service/event.service";
import {Event} from "../model/event";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrl: './event-list.component.css'
})
export class EventListComponent implements OnInit{

  events: Event[];

  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    this.eventService.findAll().subscribe(data => {
      this.events = data;
    });
  }

}
