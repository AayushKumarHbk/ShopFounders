import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Http } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/index';
import { routing } from './app.routing';
import { AuthGuard } from './pages/_guards/index';
import { AuthenticationService } from './pages/_services/index';

@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    LoginComponent,
  ],
  imports: [
    RouterModule,
    BrowserModule,
    routing,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
  ],
  exports: [],
  providers: [
    AuthGuard,
    AuthenticationService],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class AppModule { }
