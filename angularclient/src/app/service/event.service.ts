import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Event} from "../model/event";

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private eventsUrl: string;

  constructor(private http: HttpClient) {
    this.eventsUrl = 'http://localhost:8080/events';
  }

  headers = new HttpHeaders({
    'Content-Type':'application/json',
    'Access-Control-Request-Method':'POST'
  });


  public findAll(): Observable<Event[]> {
    return this.http.get<Event[]>(this.eventsUrl);
  }

  public save(data: any) {
    return this.http.post<any>(this.eventsUrl, data);
  }

  /*
  public save(event: Event) {
    return this.http.post<Event>(this.eventsUrl, event);
  }

   */
}
