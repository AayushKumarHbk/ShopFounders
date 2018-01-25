import { Routes, RouterModule } from '@angular/router';
import { LandPage } from './landpage.component';

const routes: Routes = [
  {
    path: '',
    component: LandPage,
    children: [
      { path: 'landpage', component: LandPage }
    ]
  }
];

export const routing = RouterModule.forChild(routes);
