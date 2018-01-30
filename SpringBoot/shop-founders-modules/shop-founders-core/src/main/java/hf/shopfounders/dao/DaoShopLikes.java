package hf.shopfounders.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import hf.shopfounders.model.ShopLikeType;
import hf.shopfounders.model.ShopLikeStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "LikeTable_Shop")
public class DaoShopLikes {

    @JsonIgnore
    @Id
    private String id;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("shopId")
    private String shopId;
    @JsonProperty("likeType")
    private int likeType;
    @JsonIgnore
    private Date likeDate;

    public DaoShopLikes() {
    }

    public DaoShopLikes(String userId, String shopId, int likeType, Date likeDate) {
        this.userId = userId;
        this.shopId = shopId;
        this.likeType = likeType;
        this.likeDate = likeDate;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public int getLikeType() {
        return likeType;
    }

    public void setLikeType(int likeType) {
        this.likeType = likeType;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    @Override
    public String toString() {
        return String.format(
                "DaoShop[id='%s', userId='%s', shopId='%s', likeType='%d', likeDate='%s']",
                id, userId, shopId, likeType, likeDate.toString());
    }
}
