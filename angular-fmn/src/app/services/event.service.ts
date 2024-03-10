import { Injectable } from '@angular/core';
import {EventInterface} from '../model/event-interface';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {EventAttendee} from "../model/event-attendee";
import {Observable} from "rxjs";
@Injectable({
  providedIn: 'root'
})
export class EventService {

  eventListUrl = 'http://localhost:8080/events';
  userListUrl =   `http://localhost:8080/getUserList`;
  private eventsUrl: string;

  headers = new HttpHeaders({
    'Content-Type':'application/json',
    'Access-Control-Request-Method':'POST'
  });


  async getAllEvents(): Promise<EventInterface[]> {
    const data = await fetch(this.eventListUrl);
    return await data.json() ?? [];

  }

  public getAllUsers(): Observable<EventAttendee[]>{
    return this.http.get<EventAttendee[]>(this.userListUrl);
  }

  submitEvent(data: any){
    return this.http.post<any>(this.eventsUrl, data);
  }

  constructor(private http: HttpClient) {
    this.eventsUrl = 'http://localhost:8080/events';
    this.userListUrl = `http://localhost:8080/getUserList`;
  }
}
