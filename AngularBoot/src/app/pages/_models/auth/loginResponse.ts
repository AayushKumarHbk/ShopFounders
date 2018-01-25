export class LoginResponse {
    username: string;
    password: string;
    role: string;
    loginstatus: boolean;

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

    setLoginstatus(loginstatus: boolean) {
        this.loginstatus = loginstatus;
    }

    getLoginstatus(): boolean {
        return this.loginstatus;
    }

}
