import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { AuthGuard } from './pages/_guards/index';
import { Pages } from './pages/index';
import { LoginComponent } from './pages/login/index';
import { RegisterComponent } from './pages/register/index';


export const routes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', component: Pages, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, { useHash: true });