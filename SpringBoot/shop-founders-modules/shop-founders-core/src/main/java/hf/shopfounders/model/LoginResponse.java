package hf.shopfounders.model;

public class LoginResponse {

    private String username;
    private String password;
    private boolean loginstatus;
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

    public boolean getLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(boolean loginstatus) {
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
        return this.getClass().getTypeName() + ": [" + username + ", " + password + ", " + loginstatus + token + "]";
    }
}
