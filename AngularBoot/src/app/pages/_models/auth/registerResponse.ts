/**
 * POJO to creaet Insert new user into LDAP server
 */
export class RegisterResponse {
    private firstname: string;
    private lastname: string;
    private username: string;
    private password: string;
    private userrole: string;
    private userexist: boolean;
    private insertstatus: boolean;

    public setFirstname(firstname) {
        this.firstname = firstname;
    }

    public getFirstName() {
        return this.firstname;
    }


    public setLastName(lastname) {
        this.lastname = lastname;
    }

    public getLastName() {
        return this.lastname;
    }


    public setUserName(username) {
        this.username = username;
    }

    public getUserName() {
        return this.username;
    }

    public setPassword(password) {
        this.password = password;
    }

    public getPassword() {
        return this.password;
    }

    public setuserRole(userrole) {
        this.userrole = userrole;
    }

    public getUserRole() {
        return this.userrole;
    }

    public setUserExist(userexist) {
        this.userexist = userexist;
    }

    public getUserExist() {
        return this.userexist;
    }

    public setInsertStatus(insertstatus) {
        this.insertstatus = insertstatus;
    }

    public getInsertStatus() {
        return this.insertstatus;
    }
}