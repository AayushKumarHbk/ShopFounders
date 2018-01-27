package hf.shopfounders.model;

public class LoginResponse {

    private String username;
    private String password;
    private LoginStatus loginstatus;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginStatus getLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(LoginStatus loginstatus) {
        this.loginstatus = loginstatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return String.format(
                "LoginResponse[username='%s', password='%s',loginstatus='%s', token='%s']",
                username, password, loginstatus, token);
    }
}
