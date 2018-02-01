package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveLikeResponse {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("shopId")
    private String shopId;
    @JsonProperty("likeStatus")
    private RemoveLikeStatus likeStatus;

    public RemoveLikeResponse() {
    }

    public RemoveLikeResponse(String userId, String shopId, RemoveLikeStatus likeStatus) {
        this.userId = userId;
        this.shopId = shopId;
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

    public RemoveLikeStatus getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(RemoveLikeStatus likeStatus) {
        this.likeStatus = likeStatus;
    }

    @Override
    public String toString() {
        return String.format(
                "RemoveLikeResponse[userId='%s', shopId='%s', likeStatus='%s']",
                userId, shopId, likeStatus);
    }
}
