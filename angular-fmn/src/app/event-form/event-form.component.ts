import {Component, inject} from '@angular/core';
import {ActivatedRoute, Router, RouterOutlet} from "@angular/router";
import {EventService} from "../services/event.service";
import {CommonModule, DatePipe, NgForOf} from "@angular/common";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Event} from '../model/event-interface';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatNativeDateModule} from "@angular/material/core";
import {MatInputModule} from "@angular/material/input";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {MatCheckboxModule} from '@angular/material/checkbox';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {BrowserModule, HammerModule} from "@angular/platform-browser";
import {EventAttendee} from "../model/event-attendee";
import {Observable} from "rxjs";
import {
  IGX_TIME_PICKER_DIRECTIVES,
  IgxIconComponent,
  IgxPickerToggleComponent,
  IgxTimePickerComponent
} from 'igniteui-angular';

@Component({
  selector: 'app-event-form',
  standalone: true,
  imports: [
    ReactiveFormsModule, RouterOutlet, MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    MatDialogModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCheckboxModule,
    NgForOf,
    IGX_TIME_PICKER_DIRECTIVES,
    HammerModule, IgxTimePickerComponent, IgxPickerToggleComponent, IgxIconComponent,
  ],
  templateUrl: './event-form.component.html',
  styleUrl: './event-form.component.css'
})
export class EventFormComponent {
    route: ActivatedRoute = inject(ActivatedRoute);
    eventService = inject(EventService);
    event: Event;
    router: Router;
    eventTypeHash: Map<String,String>;
    userList: EventAttendee[];
    selectedUsers: string[];
    attendees: string[];
    reminderScheduled: boolean;
    scheduledTime: string | null;
    date: Date = new Date();
    datePipe: DatePipe;


    applyForm = new FormGroup({
      name: new FormControl(''),
      description: new FormControl(''),
      scheduledDate: new FormControl(''),
      scheduledTime: new FormControl(''),
      dueDate: new FormControl(''),
      attendees: new FormControl([]),
      reminderScheduled: new FormControl(false),
    })


  constructor() {
      this.event = new Event('','','','', '');
      this.router = new Router();
    this.eventService.getAllUsers().subscribe(data => {
      this.userList = data;
    });
      this.selectedUsers = [];
      this.eventTypeHash = new Map<String,String>;
      this.eventTypeHash.set("Work Event","0");
      this.eventTypeHash.set("Social Event", "1");
      this.eventTypeHash.set("Task","2");
      this.eventTypeHash.set("","Misc");
  }

  submitEvent(){
      this.event.name = this.applyForm.value.name ?? '';
      this.event.description = this.applyForm.value.description ?? '';
      this.event.scheduledDate = this.applyForm.value.scheduledDate ?? '';
      this.event.scheduledTime = this.applyForm.value.scheduledTime ?? '';
      this.event.dueDate = this.applyForm.value.dueDate ?? '';
      this.event.attendees = this.applyForm.value.attendees ?? [];
      this.event.reminderScheduled = this.applyForm.value.reminderScheduled ?? false;

     this.eventService.submitEvent(this.event).subscribe(result => this.goToEventList());
  }

  goToEventList(){
    this.router.navigate(['/']);
  }

  selectEventType(value: string){
      this.event.eventType = this.eventTypeHash.get(value)!.toString();
  }

  selectUsers(value: string){
      this.selectedUsers.push(value);
      console.log(this.selectedUsers)
  }

  selectScheduleDate(value: string){
      this.event.scheduledDate = this.event.scheduledDate
  }

  scheduleReminder(value: boolean){
      this.reminderScheduled = this.event.reminderScheduled
  }

  selectScheduledTime(value: string | Date){
      let timeToString = this.datePipe.transform(value, 'hh:mm');
      this.scheduledTime = timeToString;
    console.log("here is time to string " + timeToString);
  }
}