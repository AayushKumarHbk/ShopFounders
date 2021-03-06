import { Routes, RouterModule } from '@angular/router';
import { Pages } from './pages.component';
import { ModuleWithProviders } from '@angular/core';
import { AuthGuard } from './_guards/index';
import { NearbyShopsComponent } from './nearbyShops/index';
import { PreferredShopsComponent } from './preferredShops/index';

export const routes: Routes = [
  {
    path: 'pages', component: Pages,
    children: [
      { path: '', component: NearbyShopsComponent, canActivate: [AuthGuard] },
      { path: 'nearbyShops', component: NearbyShopsComponent, canActivate: [AuthGuard] },
      { path: 'preferredShops', component: PreferredShopsComponent, canActivate: [AuthGuard] }
    ]
  }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);
