package hf.shopfounders.model;

public class DaoShopLocation {

    private String type;
    private double[] coordinates;

    public DaoShopLocation() {
    }

    public DaoShopLocation(String type, double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return String.format(
                "Location[coordinates='%s', coordinates='%s']",
                coordinates[0], coordinates[1]);
    }
}
