package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import hf.shopfounders.dao.DaoShop;

import java.util.List;

public class PreferredShopsResponse {

    @JsonProperty("shops")
    private List<DaoShop> shops;
    @JsonProperty("preferredShopsStatus")
    private PreferredShopsStatus preferredShopsStatus;

    public PreferredShopsResponse() {
    }

    public PreferredShopsResponse(List<DaoShop> shops, PreferredShopsStatus preferredShopsStatus) {
        this.shops = shops;
        this.preferredShopsStatus = preferredShopsStatus;
    }

    public List<DaoShop> getShops() {
        return this.shops;
    }

    public void setShops(List<DaoShop> shops) {
        this.shops = shops;
    }

    public PreferredShopsStatus getPreferredShopsStatus() {
        return preferredShopsStatus;
    }

    public void setPreferredShopsStatus(PreferredShopsStatus preferredShopsStatus) {
        this.preferredShopsStatus = preferredShopsStatus;
    }

    @Override
    public String toString() {
        return String.format(
                "PreferredShopsResponse[shops='%s', preferredShopsStatus='%s']",
                shops, preferredShopsStatus);
    }
}