import { Routes, RouterModule } from '@angular/router';
import { LandPageComponent } from './landpage.component';

const routes: Routes = [
  {
    path: '',
    component: LandPageComponent,
    children: [
      { path: 'landpage', component: LandPageComponent }
    ]
  }
];

export const routing = RouterModule.forChild(routes);
