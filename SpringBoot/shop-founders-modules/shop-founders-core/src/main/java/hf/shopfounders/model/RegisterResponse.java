package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponse {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("registerStatus")
    private RegisterStatus registerStatus;

    public RegisterResponse() {
    }

    public RegisterResponse(String username, String password, RegisterStatus registerStatus) {
        this.username = username;
        this.password = password;
        this.registerStatus = registerStatus;
    }

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
