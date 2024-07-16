import {EventAttendee} from "./event-attendee";

export interface EventInterface {
  name: string;
  description: string;
  scheduledDate: string;
  dueDate: string;
  eventType: string;
  attendees: string[];
}

export class Event  {

  name: string;
  description: string;

  scheduledDate: string;
  scheduledTime: string | Date;
  dueDate: string;
  eventType: string;
  attendees: string[];
  reminderScheduled: boolean;


    constructor(name: string, description: string, scheduledDate: string, dueDate: string, scheduledTime: string){
      this.name = name;
      this.description = description;
      this.scheduledDate = scheduledDate;
      this.dueDate = dueDate;
      this.eventType = "";
      this.attendees = [];
      this.reminderScheduled = false;
      this.scheduledTime = scheduledTime;
    };


}
