import {Component, Input} from '@angular/core';
import {EventInterface} from "../model/event-interface";

@Component({
  selector: 'app-event',
  standalone: true,
  imports: [],
  templateUrl: './event.component.html',
  styleUrl: './event.component.css'
})
export class EventComponent {
  @Input() event!: EventInterface;
}
