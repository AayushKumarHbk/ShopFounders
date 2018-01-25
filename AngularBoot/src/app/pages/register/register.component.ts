import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

import 'style-loader!./register.scss';
import { AuthenticationService } from '../_services/index';
import { RegisterRequest, RegisterResponse } from '../_models/index';

@Component({
  selector: 'register',
  templateUrl: './register.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public model: any = {};
  public loading = false;
  public failmessage = false;
  public error = '';

  constructor(private router: Router,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.model.userrole = 'user';
  }

  /**
   *  it will create LoginRequest to the entered username and password
   */
  register() {
    // remove current user details from localStorage if present
    localStorage.removeItem('currentUser');
    // create an object containing the username, passowrd and userRole
    const registerRequest = new RegisterRequest();
    registerRequest.setUsername(this.model.username);
    registerRequest.setPassword(this.model.password);
    registerRequest.setUserRole(this.model.userrole);
    // Call Authentication Service for initiating the RegisterRequest
    console.log('Calling AuthenticationService...');
    this.authenticationService.register(registerRequest).subscribe(
      (data) => {
        if (data) {
          // Register successful, therefore navigate to another page
          console.log('navigating to login...');
          this.failmessage = false;
          this.router.navigate(['/login']);
        } else if (data === false) {
          // login failed
          this.error = 'Registration Failed';
          this.loading = false;
          this.failmessage = true;
        }
      }
    );
  }

}
