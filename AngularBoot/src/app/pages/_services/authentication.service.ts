import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import { Router } from '@angular/router';

import { LoginRequest, LoginStatus, LoginResponse, RegisterRequest, RegisterStatus, RegisterResponse } from '../_models/index';
import { WebserviceErrorHandler } from '../errohandler/errorhandler.component';

@Injectable()
export class AuthenticationService {
    public token: string;
    private _url_Auth_Register = 'http://localhost:8080/auth/register';
    private _url_Auth_Login = 'http://localhost:8080/auth/login';
    http: Http;
    private authConnectionAttempt: number;
    public errorhandler: WebserviceErrorHandler;

    constructor(private router: Router,
        http: Http) {
        this.http = http;
        // set token if saved in local storage
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.token = currentUser && currentUser.token;
        this.errorhandler = new WebserviceErrorHandler();
    }

    login(username: string, password: string): Observable<boolean> {
        return this.http.post(this._url_Auth_Login, JSON.stringify({ username: username, password: password }))
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
    loginRest(request: LoginRequest): Observable<LoginResponse> {
        this.authConnectionAttempt = 0;

        this._url_Auth_Login = 'http://localhost:8080/auth/login';

        // check if request is null
        if (request && request.getUserName() && request.getUserName() && request.getUserRole()) {
            const castedRequest: string = JSON.stringify(request);
            /*check if user is already present in the localStorage
            *if present, then simply redirect to another page
            *if not present, then call the Authentication service
            */
            if (this.localStorageCheckUser(castedRequest) === true) {
                console.log('User found in localStorage');
                const responsePackage: LoginResponse = new LoginResponse();
                const statusPackage: LoginStatus = new LoginStatus();
                statusPackage.setStatus(true);
                statusPackage.setMessage('User already logged in!!');
                responsePackage.setLoginStatus(statusPackage);
                return Observable.of(responsePackage);
            } else {
                console.log('User not found in localStorage');

                // send login request to Auth Service
                // attaching headers to the request
                const headers: Headers = new Headers();
                headers.append('Content-Type', 'application/json');
                headers.append('Accept', 'application/json');
                const options: RequestOptions = new RequestOptions({ headers });

                console.log('Connecting to Auth Service...');
                console.log('Login Request: ' + castedRequest);

                // making a post request to Auth Service
                return this.http.post(this._url_Auth_Login, request, options)
                    .map(data => this.extractLoginAuthData(data))
                    .catch(this.errorhandler.handleError);
            }
        }
    }

    /**
     * Get the response data for login request
     */
    private extractLoginAuthData(res: Response): LoginResponse {
        this.authConnectionAttempt = 1;
        const responsePackage: LoginResponse = new LoginResponse();
        const statusPackage: LoginStatus = new LoginStatus();
        console.log('Auth Connection Attempt successful\nLoginResponse obtained from Auth Service');
        if (res.status === 200 && res.statusText === 'OK') {
            const responseArray = JSON.parse(res.text());
            console.log('Login Response: ' + JSON.stringify(responseArray));
            if (responseArray.loginStatus != null
                && responseArray.loginStatus.status != null
                && responseArray.loginStatus.message != null) {
                statusPackage.setStatus(responseArray.loginStatus.status);
                statusPackage.setMessage(responseArray.loginStatus.message);
                if (statusPackage.getStatus()) {
                    responsePackage.setUsername(responseArray.username);
                    responsePackage.setPassword(responseArray.password);
                    if (this.localStorageAddUser(responseArray) === false) {
                        console.log('User [', responsePackage.getUsername(), '] added to localStorage successfully!!');
                    } else {
                        console.log('User [', responsePackage.getUsername(), '] could not be added to localStorage');
                    }
                }
            }
        } else {
            console.log('abnormal response' + res);
        }
        responsePackage.setLoginStatus(statusPackage);
        console.log('AuthenticationService::login [EXIT]');
        return responsePackage;
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

    /**
     * Create insert request to authentication server to mentioned url
     */
    register(request: RegisterRequest): Observable<RegisterResponse> {
        console.log('AuthenticationService::register [ENTER]');
        // check if request is null
        if (request && request.getUserName() && request.getUserName() && request.getUserRole()) {
            // send register request to Auth Service
            // attaching headers to the request
            const headers: Headers = new Headers();
            headers.append('Content-Type', 'application/json');
            headers.append('Accept', 'application/json');
            const options: RequestOptions = new RequestOptions({ headers });

            console.log('Connecting to Auth Service...');
            console.log('Register Request: ' + JSON.stringify(request));

            // making a post request to Auth Service
            return this.http.post(this._url_Auth_Register, request, options)
                .map(data => this.extractRegisterAuthData(data))
                .catch(this.errorhandler.handleError);
        }
    }

    /**
     * Get the response data for register request
     */
    private extractRegisterAuthData(res: Response): RegisterResponse {
        this.authConnectionAttempt = 1;
        const responsePackage: RegisterResponse = new RegisterResponse();
        const statusPackage: RegisterStatus = new RegisterStatus();
        console.log('Auth Connection Attempt successful\nRegisterResponse obtained from Auth Service');
        if (res.status === 200 && res.statusText === 'OK') {
            const responseArray = JSON.parse(res.text());
            console.log('Register Response: ' + JSON.stringify(responseArray));
            if (responseArray.registerStatus != null
                && responseArray.registerStatus.status != null
                && responseArray.registerStatus.message != null) {
                statusPackage.setStatus(responseArray.registerStatus.status);
                statusPackage.setMessage(responseArray.registerStatus.message);
                if (statusPackage.getStatus()) {
                    responsePackage.setUsername(responseArray.username);
                    responsePackage.setPassword(responseArray.password);
                    console.log('User [', responsePackage.getUsername(), '] registered successfully!!');
                }
            }
        } else {
            console.log('abnormal response' + res);
        }
        responsePackage.setRegisterStatus(statusPackage);
        console.log('AuthenticationService::register [EXIT]');
        return responsePackage;
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        this.token = null;
        localStorage.removeItem('currentUser');
    }
}
