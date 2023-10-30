package src.test.model.inventory;

public abstract class ProductMock {
    private double cost;
    private double sellingPrice;

    // EFFECTS: constructs a product with its cost and sellingPrice
    public ProductMock(double cost, double sellingPrice) {
        this.cost = cost;
        this.sellingPrice = sellingPrice;
    }

    public double getCost() {
        return cost;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double newPrice) {
        this.sellingPrice = newPrice;
    }

}
