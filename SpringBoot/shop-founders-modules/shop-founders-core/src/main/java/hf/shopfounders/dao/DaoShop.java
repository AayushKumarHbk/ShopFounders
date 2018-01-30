package hf.shopfounders.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import hf.shopfounders.model.DaoShopLocation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shops")
public class DaoShop {

    @Id
    @JsonProperty("_id")
    private String _id;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("city")
    private String city;
    @JsonProperty("location")
    private DaoShopLocation location;

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public DaoShopLocation getLocation() {
        return this.location;
    }

    public void setLocation(DaoShopLocation location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format(
                "DaoShop[_id='%s', picture='%s', name='%s', emai='%s', city='%s', location='%s']",
                _id, picture, name, email, city, location);
    }
}
