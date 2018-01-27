import {LoginStatus} from './loginStatus';

export class LoginResponse {
    username: string;
    password: string;
    role: string;
    loginStatus: LoginStatus;

    setUsername(username: string) {
        this.username = username;
    }

    getUsername(): string {
        return this.username;
    }

    setPassword(username: string) {
        this.password = username;
    }

    getPassword(): string {
        return this.password;
    }

    setUserRole(role: string) {
        this.role = role;
    }

    getUserRole(): string {
        return this.role;
    }

    setLoginStatus(loginStatus: LoginStatus) {
        this.loginStatus = loginStatus;
    }

    getLoginStatus(): LoginStatus {
        return this.loginStatus;
    }

}
