/**
 * POJO to creaet Insert new user into LDAP server
 */
export class RegisterRequest {
    username: string;
    password: string;
    role: string;

    public constructor() { }

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
}
