import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserListService {
  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/getUserList';
  }

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Request-Method': 'POST'
  });


  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  public save(data: any) {
    return this.http.post<any>(this.usersUrl, data);
  }

}
