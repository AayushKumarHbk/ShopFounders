export class LoginStatus {
    private loginstatus: boolean;

    public setLoginStatus(loginstatus: boolean) {
        this.loginstatus = loginstatus;
    }

    public getLoginStatus():boolean {
        return this.loginstatus;
    }

}