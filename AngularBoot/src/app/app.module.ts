import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Http } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/index';
import { routing } from './app.routing';
import { PagesModule } from './pages/pages.module';
import { NgaModule } from './theme/nga.module';
import { AuthGuard } from './pages/_guards/index';
import { AuthenticationService, ShopManagementService } from './pages/_services/index';
import { RegisterComponent } from './pages/register/index';

@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
  ],
  imports: [
    RouterModule,
    BrowserModule,
    routing,
    PagesModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    NgaModule,
  ],
  exports: [],
  providers: [
    AuthGuard,
    AuthenticationService,
    ShopManagementService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class AppModule { }
