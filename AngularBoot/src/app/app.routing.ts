import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { AuthGuard } from './pages/_guards/index';
import { LoginComponent } from './pages/login/index';


export const routes: Routes = [

  { path: '', component: LoginComponent },
  { path: '**', redirectTo: '' }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, { useHash: true });