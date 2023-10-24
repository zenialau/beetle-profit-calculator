package src.main.model.inventory;

// Represents a container used to make the mounted display with type, size, cost
public class Container {
    private String type;
    private String size;
    private double cost;

    // Constructs a container with type size and cost
    public Container(String type, String size, double cost) {
        this.type = type;
        this.size = size;
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public double getCost() {
        return cost;
    }
}
