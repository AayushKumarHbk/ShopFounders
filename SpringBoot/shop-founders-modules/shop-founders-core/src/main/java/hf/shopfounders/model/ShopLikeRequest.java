package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO representing a request for liking a shop
 */
public class ShopLikeRequest {

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
     * type of like i.e. like or dislike
     */
    @JsonProperty("likeType")
    private int likeType;

    /**
     * default, non-parameterized constructor
     */
    public ShopLikeRequest() {
    }

    /**
     * parameterized constructor
     *
     *  @param userId unique user ID
     *  @param shopId unique shop ID
     *  @param likeType type of like for a shop
     */
    public ShopLikeRequest(String userId, String shopId, int likeType) {
        this.userId = userId;
        this.shopId = shopId;
        this.likeType = likeType;
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
     * gets type of like for a shop
     *
     * @return likeType type of like for a shop
     */
    public int getLikeType() {
        return likeType;
    }

    /**
     * sets type of like for a shop
     * 
     *  @param likeType type of like for a shop
     */
    public void setLikeType(int likeType) {
        this.likeType = likeType;
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
                "ShopLikeRequest[userId='%s', shopId='%s', likeType='%s']",
                userId, shopId, likeType);
    }
}
