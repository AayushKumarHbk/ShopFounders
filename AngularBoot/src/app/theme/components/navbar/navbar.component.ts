import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.scss']
})
export class NavBarComponent {

  public isScrolled = false;

  constructor() {
  }

  public scrolledChanged(isScrolled) {
    this.isScrolled = isScrolled;
  }
}
