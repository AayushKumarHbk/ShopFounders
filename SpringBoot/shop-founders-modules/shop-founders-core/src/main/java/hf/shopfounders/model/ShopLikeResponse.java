package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopLikeResponse {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("shopId")
    private String shopId;
    @JsonProperty("likeType")
    private int likeType;
    @JsonProperty("likeStatus")
    private ShopLikeStatus likeStatus;

    public ShopLikeResponse() {
    }

    public ShopLikeResponse(String userId, String shopId, int likeType, ShopLikeStatus likeStatus) {
        this.userId = userId;
        this.shopId = shopId;
        this.likeType = likeType;
        this.likeStatus = likeStatus;
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

    public ShopLikeStatus getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(ShopLikeStatus likeStatus) {
        this.likeStatus = likeStatus;
    }

    @Override
    public String toString() {
        return String.format(
                "ShopLikeResponse[userId='%s', shopId='%s', likeType='%s', likeStatus='%s']",
                userId, shopId, likeType, likeStatus);
    }
}
