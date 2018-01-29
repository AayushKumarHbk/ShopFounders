import { Component } from '@angular/core';
import {
  Router
} from '@angular/router';

@Component({
  selector: 'app-root',
  styleUrls: ['./app.component.css'],
  template: `
    <main [class.menu-collapsed]="isMenuCollapsed" baThemeRun>
      <div class="additional-bg"></div>
      <router-outlet></router-outlet>
    </main>
  `
})
export class AppComponent {
  title = 'app';

  constructor(private router: Router) {
  }

}
