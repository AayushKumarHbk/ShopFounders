package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import hf.shopfounders.exception.BaseErrorCode;
import hf.shopfounders.validation.ParamNotNull;

public class RegisterRequest {

    @ParamNotNull
    @JsonProperty(value = "username")
    private String username;
    @ParamNotNull
    @JsonProperty(value = "password")
    private String password;
    @ParamNotNull
    @JsonProperty(value = "role")
    private String role;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format(
                "RegisterRequest[username='%s', password='%s',role='%s']",
                username, password, role);
    }
}
