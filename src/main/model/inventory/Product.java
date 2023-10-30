package src.main.model.inventory;

// abstract class that contains any kind of product that could be sold (Beetle, MountedDisplay)
public abstract class Product {
    protected double cost;
    protected double sellingPrice;

    // EFFECTS: constructs a product with its cost and sellingPrice
    protected Product(double cost, double sellingPrice) {
        this.cost = cost;
        this.sellingPrice = sellingPrice;
    }

    public double getCost() {
        return cost;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    // REQUIRES: price >= 0
    // MODIFIES: this
    // EFFECTS: set a new selling price for this product
    public void setSellingPrice(double newPrice) {
        this.sellingPrice = newPrice;
    }

}
