package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "password")
    private String password;
    @JsonProperty(value = "role")
    private String role;

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
        return this.getClass().getTypeName() + ": [" + username + ", " + password + ", " + role + "]";
    }
}
