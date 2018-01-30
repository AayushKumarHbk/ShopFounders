package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopLikeStatus {

    @JsonProperty("status")
    private boolean status;
    @JsonProperty("message")
    private String message;

    public ShopLikeStatus() {
    }

    public ShopLikeStatus(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(
                "ShopLikeStatus[status='%s', message='%s']",
                status, message);
    }
}