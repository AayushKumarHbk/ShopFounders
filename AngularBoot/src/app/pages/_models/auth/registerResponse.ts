import {RegisterStatus} from './registerStatus';

export class RegisterResponse {
    private username: string;
    private password: string;
    private registerStatus: RegisterStatus;

    public setUsername(username) {
        this.username = username;
    }

    public getUsername() {
        return this.username;
    }

    public setPassword(password) {
        this.password = password;
    }

    public getPassword() {
        return this.password;
    }

    setRegisterStatus(registerStatus: RegisterStatus) {
        this.registerStatus = registerStatus;
    }

    getRegisterStatus(): RegisterStatus {
        return this.registerStatus;
    }

}
