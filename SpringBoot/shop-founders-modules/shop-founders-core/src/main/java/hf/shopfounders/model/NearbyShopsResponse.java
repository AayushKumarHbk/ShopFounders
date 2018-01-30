package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import hf.shopfounders.dao.DaoShop;

import java.util.List;

public class NearbyShopsResponse {

    @JsonProperty("shops")
    private List<DaoShop> shops;
    @JsonProperty("nearbyShopsStatus")
    private NearbyShopsStatus nearbyShopsStatus;

    public NearbyShopsResponse() {
    }

    public NearbyShopsResponse(List<DaoShop> shops, NearbyShopsStatus nearbyShopsStatus) {
        this.shops = shops;
        this.nearbyShopsStatus = nearbyShopsStatus;
    }

    public List<DaoShop> getShops() {
        return this.shops;
    }

    public void setShops(List<DaoShop> shops) {
        this.shops = shops;
    }

    public NearbyShopsStatus getNearbyShopsStatus() {
        return nearbyShopsStatus;
    }

    public void setNearbyShopsStatus(NearbyShopsStatus nearbyShopsStatus) {
        this.nearbyShopsStatus = nearbyShopsStatus;
    }
}
