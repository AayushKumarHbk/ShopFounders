/**
 * POJO to creaet Insert new user into LDAP server
 */
export class RegisterResponse {
    private username: string;
    private password: string;
    private registerstatus: boolean;

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

    setRegisterstatus(registerstatus: boolean) {
        this.registerstatus = registerstatus;
    }

    getRegisterstatus(): boolean {
        return this.registerstatus;
    }

}
