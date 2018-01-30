package hf.shopfounders.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import hf.shopfounders.dao.DaoShop;

import java.util.List;

public class GetAllShopsResponse {

    @JsonProperty("shops")
    private List<DaoShop> shops;
    @JsonProperty("getAllShopsStatus")
    private GetAllShopsStatus getAllShopsStatus;

    public GetAllShopsResponse() {
    }

    public GetAllShopsResponse(List<DaoShop> shops, GetAllShopsStatus getAllShopsStatus) {
        this.shops = shops;
        this.getAllShopsStatus = getAllShopsStatus;
    }

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
