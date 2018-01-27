import {LoginStatus} from './loginStatus';

export class LoginResponse {
    username: string;
    password: string;
    role: string;
    loginstatus: LoginStatus;

    setUsername(username: string) {
        this.username = username;
    }

    getUserName(): string {
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

    setLoginstatus(loginstatus: LoginStatus) {
        this.loginstatus = loginstatus;
    }

    getLoginstatus(): LoginStatus {
        return this.loginstatus;
    }

}
