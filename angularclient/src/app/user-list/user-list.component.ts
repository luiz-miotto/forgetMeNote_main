import {Component, OnInit} from '@angular/core';
import {Event} from "../model/event";
import {EventService} from "../service/event.service";
import {User} from "../model/user";
import {UserListService} from "../service/user-list.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit{

  users: User[];

  constructor(private userService: UserListService) {
  }

  ngOnInit() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }
}
