package hf.shopfounders.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SFRegisteredUsers")
public class DaoUser {

    @JsonIgnore
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String role;

    public DaoUser() {
    }

    public DaoUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() { return id; }

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
                "DaoUser[id=%s, username='%s', password='%s']",
                id, username, password);
    }
}
