package src.main.model.inventory;

// Represent a specific kind of beetle with latin name, cost, and size in mm
public class Beetle {
    private String latinName;
    private double cost;
    private int size;

    // Construct a beetle with its Latin name, cost and size (in mm)
    public Beetle(String latinName, double cost, int size) {
        this.latinName = latinName;
        this.cost = cost;
        this.size = size;
    }

    public String getLatinName() {
        return latinName;
    }

    public double getCost() {
        return cost;
    }

    public int getSize() {
        return size;
    }

}
