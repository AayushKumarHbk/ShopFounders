package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    /*
     *  Method to check if request is valid
     * */
    @JsonIgnore
    public boolean isValid() {
        if((this.username != null && !this.username.isEmpty())
                || (this.password == null && !this.password.isEmpty())
                || this.role == null && !this.role.isEmpty())
            return true;
        return false;
    }
}
