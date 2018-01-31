package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PreferredShopsRequest {

    @JsonProperty("username")
    private String username;

    public PreferredShopsRequest() {
    }

    public PreferredShopsRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format(
                "PreferredShopsRequest[username='%s']",
                username);
    }
}