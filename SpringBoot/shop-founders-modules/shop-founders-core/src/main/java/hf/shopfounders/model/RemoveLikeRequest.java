package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveLikeRequest {

    /**
     * user id
     */
    @JsonProperty("userId")
    private String userId;
    /**
     * shop id
     */
    @JsonProperty("shopId")
    private String shopId;

    /**
     * default, non-parameterized constructor
     */
    public RemoveLikeRequest() {
    }

    /**
     * parameterized constructor
     *
     *  @param userId unique user ID
     *  @param shopId unique shop ID
     */
    public RemoveLikeRequest(String userId, String shopId) {
        this.userId = userId;
        this.shopId = shopId;
    }

    /**
     * gets user id
     *
     * @return userId unique ID of the user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * sets user id
     *
     *  @param userId unique user ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * gets shop id
     *
     * @return shopId unique ID of a shop
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * sets shop id
     *
     *  @param shopId unique shop ID
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * override the default implenentation of toString() method
     * so as to provide our own implementation
     *
     * @return a formatted string representing the request
     */
    @Override
    public String toString() {
        return String.format(
                "ShopLikeRequest[userId='%s', shopId='%s']",
                userId, shopId);
    }
}
