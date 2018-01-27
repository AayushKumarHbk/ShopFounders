import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';

import 'style-loader!./register.scss';
import { AuthenticationService } from '../_services/index';
import { RegisterRequest, RegisterStatus, RegisterResponse } from '../_models/index';

@Component({
  selector: 'register',
  templateUrl: './register.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public model: any = {};
  public loading = false;
  public failmessage = false;
  public message = '';

  constructor(private router: Router,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.model.userrole = 'user';
  }

  /**
   *  it will create LoginRequest to the entered username and password
   */
  register() {
    console.log('RegisterComponent::register [ENTER]');
    this.loading = true;
    this.message = 'registering user...';
    this.failmessage = true;
    // remove current user details from localStorage if present
    localStorage.removeItem('currentUser');
    // create an object containing the username, password and userRole
    const registerRequest = new RegisterRequest();
    registerRequest.setUsername(this.model.username);
    registerRequest.setPassword(this.model.password);
    registerRequest.setUserRole(this.model.userrole);
    // Call Authentication Service for initiating the RegisterRequest
    console.log('Calling AuthenticationService...');
    this.authenticationService.register(registerRequest).subscribe(
      (data) => {
        const status: RegisterStatus = data.getRegisterStatus();
        if (status.getMessage() != null) {
          this.message = status.getMessage();
          console.log(status.getMessage());
          this.failmessage = true;
          this.loading = false;
        } else { this.failmessage = false; }
        if (status.getStatus()) {
          // Register successful, therefore navigate to another page
          console.log('navigating to login...');
         this.router.navigate(['/login']);
        }
      }
    );
    console.log('RegisterComponent::register [EXIT]');
  }

}
