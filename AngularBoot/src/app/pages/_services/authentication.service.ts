import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import { Router } from '@angular/router';

import { LoginRequest, LoginStatus, LoginResponse } from '../_models/index';
import { WebserviceErrorHandler} from '../errohandler/errorhandler.component';

@Injectable()
export class AuthenticationService {
    public token: string;
    private _url_Auth_Login;
    http: Http;
    private authConnectionAttempt: number;
    private loginresponse: LoginResponse;
    private logindata: LoginStatus;
    public errorhandler: WebserviceErrorHandler;

    constructor(private router: Router,
        http: Http) {
        this.http = http;
        // set token if saved in local storage
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
        this.loginresponse = new LoginResponse();
        this.logindata = new LoginStatus();
        this.errorhandler = new WebserviceErrorHandler();
    }

    login(username: string, password: string): Observable<boolean> {
        return this.http.post('http://localhost:8080/auth/login', JSON.stringify({ username: username, password: password }))
            .map((response: Response) => {
                // login successful if there's a jwt token in the response

                const token = response.json() && response.json().token;
                if (token) {
                    // set token property
                    this.token = token;

                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));

                    // return true to indicate successful login
                    return true;
                } else {
                    // return false to indicate failed login
                    return false;
                }
            });
    }

    /**
     * It will call login REST API with requested username and password
     */
    loginRest(request: LoginRequest): Observable<boolean> {
        this.authConnectionAttempt = 0;

        this._url_Auth_Login = 'http://localhost:8080/auth/login';

        // check if request is null
        if (request != null || request !== {}) {
            const castedRequest: string = JSON.stringify(request);
            /*check if user is already present in the localStorage
            *if present, then simply redirect to another page
            *if not present, then call the Authentication service
            */
            if (this.localStorageCheckUser(castedRequest) === true) {
                console.log('User found in localStorage');
                this.logindata.setLoginStatus(true);

                return Observable.of(this.logindata.getLoginStatus());

            } else {
                console.log('User not found in localStorage');
                this.logindata.setLoginStatus(false);

                // send login request to Auth Service
                // attaching headers to the request
                const headers: Headers = new Headers();
                headers.append('Content-Type', 'application/json');
                headers.append('Accept', 'application/json');
                const options: RequestOptions = new RequestOptions({ headers });

                console.log('Connecting to Auth Service...');
                console.log('Login Request: ' + castedRequest);

                // making a post request to Auth Service
                return this.http.post(this._url_Auth_Login, castedRequest, options)
                    .map(data => this.extractloginAuthData(data))
                    .catch(this.errorhandler.handleError);
            }
        }
    }

    /**
     * Get the response data for login request
     */
    private extractloginAuthData(res: Response): boolean {
        this.authConnectionAttempt = 1;
        console.log('Auth Connection Attempt successful\nLoginResponse obtained from Auth Service');
        if (res.status === 200 && res.statusText === 'OK') {
            const body = res.text();
            console.log('Login Response: ' + body);
            const responseArray = JSON.parse(body);
            if (responseArray.loginstatus) {
                console.log('Login successful');
                if (this.localStorageAddUser(responseArray) === true) {
                    this.logindata.setLoginStatus(true);
                } else {
                    console.log('User couldn\'t be added to localStorage\nLogin Failed');
                    this.logindata.setLoginStatus(false);
                }
                return this.logindata.getLoginStatus();
            } else {
                console.log('Login failed');
            }
        } else {
            console.log('abnormal response' + res);
        }
        return this.logindata.getLoginStatus();
    }

    /**
     * check user is exist or not in localstorage
     */
    localStorageCheckUser(currentUser): boolean {
        const parsedCurrentUser = JSON.parse(currentUser);

        // array holding list of all users
        const usersArray = new Array();
        usersArray.push(parsedCurrentUser);

        /*localStorage.setItem('users', JSON.stringify(usersArray));*/

        console.log('Searching for user' + parsedCurrentUser.username + ' in the localStorage');
        const users: any[] = JSON.parse(localStorage.getItem('users')) || [];
        const duplicateUser = users.filter(
            user => user.username === parsedCurrentUser.username
                && user.password === parsedCurrentUser.password
                && user.userrole === parsedCurrentUser.userrole).length;
        if (duplicateUser) {
            localStorage.setItem('currentUser', JSON.stringify(duplicateUser));
            return true;
        } else {
            return false;
        }
    }

    /**
     *  Add user into local storage if user not present in localstorage
     */
    localStorageAddUser(parsedCurrentUser): boolean {
        console.log('Adding user [' + parsedCurrentUser.username + '] to localStorage');

        // array holding list of all users
        const usersArray = new Array();
        usersArray.push(parsedCurrentUser);

        // array holding current user
        const currentUserArray = new Array();
        currentUserArray.push(parsedCurrentUser);

        // Dedup the currentUser from localStorage and accordingly, add the user to localStorage
        console.log('Searching for user [' + parsedCurrentUser.username + '] in the localStorage');
        const users: any[] = JSON.parse(localStorage.getItem('users')) || [];
        const duplicateUser = users.filter(user => user.username === parsedCurrentUser.username).length;
        if (duplicateUser) {
            console.log('user already present in localStorage');
            return true;
        } else {
            // add data in localStorage
            localStorage.setItem('users', JSON.stringify(usersArray));
            localStorage.setItem('currentUser', JSON.stringify(currentUserArray));
            console.log('user added to localStorage');
            return true;
        }
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.token = null;
        localStorage.removeItem('currentUser');
    }
}
