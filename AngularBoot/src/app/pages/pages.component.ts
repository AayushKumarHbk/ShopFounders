import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.css'],

})
export class Pages {

  constructor(private router: Router) {
  }

}
