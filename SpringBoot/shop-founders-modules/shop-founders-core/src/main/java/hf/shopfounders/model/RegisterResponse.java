package hf.shopfounders.model;

public class RegisterResponse {

    private String username;
    private String password;
    private RegisterStatus registerStatus;

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

    public RegisterStatus getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(RegisterStatus registerStatus) {
        this.registerStatus = registerStatus;
    }

    @Override
    public String toString() {
        return String.format(
                "RegisterResponse[username='%s', password='%s',registerStatus='%s']",
                username, password, registerStatus);
    }
}
