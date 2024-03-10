import {Component, inject} from '@angular/core';
import {EventService} from "../services/event.service";
import {EventComponent} from "../event/event.component";
import {CommonModule} from "@angular/common";
import {EventInterface} from "../model/event-interface";
import {RouterModule} from "@angular/router";
import {LogService} from "../services/log.service";
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    EventComponent, CommonModule, RouterModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
    eventList: EventInterface[] = [];
    eventService: EventService = inject(EventService);
    filteredEventList: EventInterface[] = [];
    title = "Forget Me Note";

    constructor() {
      this.eventService.getAllEvents().then((eventList:
      EventInterface[]) => {
        this.eventList = eventList;
        // not yet implemented to commenting this out this.filteredEventList = filteredEventList;
      });
    }


}
