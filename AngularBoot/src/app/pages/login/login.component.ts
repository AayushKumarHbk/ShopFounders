import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

import 'style-loader!./login.scss';
import { AuthenticationService } from '../_services/index';
import { LoginRequest, LoginStatus, LoginResponse } from '../_models/index';

@Component({
  selector: 'login',
  templateUrl: './login.html',
})

export class LoginComponent implements OnInit {

  public model: any = {};
  public loading = false;
  public failmessage = false;
  public message = '';

  constructor(private router: Router,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.model.userrole = 'user';
  }

  login() {
    this.loading = true;
    console.log(this.model.username);
    console.log(this.model.password);
    console.log(this.model.userrole);
    if (this.model.username != null && this.model.password != null) {
      // this.router.navigate(['/pages/landpage']);
      this.authenticationService.login(this.model.username, this.model.password)
        .subscribe(result => {
          if (result === true) {
            // login successful
            this.failmessage = false;
            console.log(this.failmessage);
            this.router.navigate(['/pages/landpage']);
          } else {
            // login failed
            this.message = 'Username or password is incorrect';
            this.loading = false;
            this.failmessage = true;
          }
        });
    } else {
      console.log('Provide mantadory fields');
    }

  }

  /**
   *  it will create LoginRequest to the entered username and password
   */
  loginRest() {
    console.log('LoginComponent::loginRest [ENTER]');
    this.loading = true;
    this.message = 'logging in...';
    this.failmessage = true;
    // remove current user details from localStorage if present
    localStorage.removeItem('currentUser');
    // create an object containing the username, passowrd and userRole
    const loginRequest = new LoginRequest();
    loginRequest.setUsername(this.model.username);
    loginRequest.setPassword(this.model.password);
    loginRequest.setUserRole(this.model.userrole);

    // Call Authentication Service for initiating the LoginRequest
    console.log('Calling AuthenticationService...');
    this.authenticationService.loginRest(loginRequest).subscribe(
      (data) => {
        this.loading = false;
        const status: LoginStatus = data.getLoginStatus();
        if (status.getMessage()) {
          this.message = status.getMessage();
          console.log(status.getMessage());
          this.failmessage = true;
        } else { this.failmessage = false; }
        if (status.getStatus()) {
          // Login successful, therefore navigate to Landpage
          console.log('navigating to landpage...');
          this.router.navigate(['/pages/landpage']);
        }
      }
    );
    console.log('LoginComponent::loginRest [EXIT]');
  }
}
