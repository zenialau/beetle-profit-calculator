package src.main.model.oldinventory;

// Represents a container used to make the mounted display with type, size, cost
public class Container {
    private double cost;
    private String type;
    private String size;

    // Constructs a container with type, size and cost
    public Container(String type, String size,double cost) {
        this.cost = cost;
        this.type = type;
        this.size = size;
    }

    public double getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

}
