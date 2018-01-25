import { Routes, RouterModule } from '@angular/router';
import { Pages } from './pages.component';
import { ModuleWithProviders } from '@angular/core';
import { AuthGuard } from './_guards/index';
// noinspection TypeScriptValidateTypes

// export function loadChildren(path) { return System.import(path); };

export const routes: Routes = [
  {
    path: 'pages',
    component: Pages,
    children: [
      { path: '', redirectTo: 'landpage', pathMatch: 'full', canActivate: [AuthGuard] },
      { path: 'landpage', loadChildren: './landpage/landpage.module#LandpageModule', canActivate: [AuthGuard] },
    ]
  }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);
