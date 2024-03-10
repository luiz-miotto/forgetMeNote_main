import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {HomeComponent} from "./home/home.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HomeComponent],
  template: `

  <app-home></app-home>
  `,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-fmn';
}
