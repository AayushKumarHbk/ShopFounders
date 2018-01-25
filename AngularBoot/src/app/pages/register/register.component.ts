import { Component, OnInit } from '@angular/core';
import { FormGroup, AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
  private person: RegisterRequest;

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
    this.person = new RegisterRequest();
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
