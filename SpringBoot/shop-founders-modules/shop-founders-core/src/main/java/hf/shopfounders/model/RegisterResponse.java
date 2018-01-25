package hf.shopfounders.model;

public class RegisterResponse {

    private String username;
    private String password;
    private boolean registerStatus;

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

    public boolean getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(boolean registerStatus) {
        this.registerStatus = registerStatus;
    }

    @Override
    public String toString() {
        return this.getClass().getTypeName() + ": [" + username + ", " + password + ", " + registerStatus + "]";
    }
}
