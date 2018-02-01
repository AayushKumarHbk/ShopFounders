import { Component } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../../../pages/_services/index';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.scss']
})
export class NavBarComponent {

  public isScrolled = false;

  constructor(private router: Router,
    private authenticationService: AuthenticationService) {
  }

  public scrolledChanged(isScrolled) {
    this.isScrolled = isScrolled;
  }

  public logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
