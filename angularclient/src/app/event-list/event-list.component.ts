import {Component, OnInit} from '@angular/core';
import {EventService} from "../service/event.service";
import {Event} from "../model/event";
import {User} from "../model/user";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrl: './event-list.component.css'

})
export class EventListComponent implements OnInit{

  events: Event[];
  usersUrl: string;

  constructor(private eventService: EventService, private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/getUserList';
  }

  ngOnInit() {
    this.eventService.findAll().subscribe(data => {
      this.events = data;
    });
  }

  public findAll(): Observable<User[]>{
    return this.http.get<User[]>(this.usersUrl);
  }

}
