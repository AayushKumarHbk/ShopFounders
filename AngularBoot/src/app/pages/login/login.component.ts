import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

import 'style-loader!./login.scss';
import { AuthenticationService } from '../_services/index';
import { LoginRequest, LoginResponse } from '../_models/index';

@Component({
  selector: 'login',
  templateUrl: './login.html',
})

export class LoginComponent implements OnInit {

  public model: any = {};
  public loading = false;

  public failmessage = false;
  public error = '';
  private person: LoginRequest;

    constructor(private router: Router,
    private authenticationService: AuthenticationService) {
    }

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
            this.error = 'Username or password is incorrect';
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
    // remove current user details from localStorage if present
    localStorage.removeItem('currentUser');
    // create an object containing the username, passowrd and userRole
    this.person = new LoginRequest();
    this.person.setUsername(this.model.username);
    this.person.setPassword(this.model.password);
    this.person.setUserRole('admin');
    // this.person.setUserRole(this.model.userrole);
    // Call Authentication Service for initiating the LoginRequest
    console.log('Calling AuthenticationService...');
    this.authenticationService.loginRest(this.person).subscribe(
      (data) => {
        console.log('returned data: ', data);
        if (data === true) {
          // login successful, therefore navigate to another page
          console.log('navigating to landpage...');
          this.failmessage = false;
          this.router.navigate(['/pages/landpage']);
        } else if (data === false) {
          // login failed
          this.error = 'Username or password is incorrect';
          this.loading = false;
          this.failmessage = true;
        }
      }
    );
  }

}
