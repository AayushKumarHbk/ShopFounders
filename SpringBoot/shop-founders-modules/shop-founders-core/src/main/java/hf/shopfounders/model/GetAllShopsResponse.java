package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetAllShopsResponse {

    @JsonProperty("shops")
    private List<DaoShop> shops;
    @JsonProperty("getAllShopsStatus")
    private GetAllShopsStatus getAllShopsStatus;

    public List<DaoShop> getShops() {
        return this.shops;
    }

    public void setShops(List<DaoShop> shops) {
        this.shops = shops;
    }

    public GetAllShopsStatus getGetAllShopsStatus() {
        return this.getAllShopsStatus;
    }

    public void setGetAllShopsStatus(GetAllShopsStatus getAllShopsStatus) {
        this.getAllShopsStatus = getAllShopsStatus;
    }
}
