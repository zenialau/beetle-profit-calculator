package src.main.model.inventory;

// Represent a mounted display, with beetle, container, and time spent on it, and selling price
public class MountedDisplay {
    private Beetle beetle;
    private Container container;
    private int timeSpent;
    private double sellingPrice;

    // Constructs a mounted display for sale with a beetle, container, time spent (mins)
    // and its selling price
    public MountedDisplay(Beetle beetle, Container container, int timeSpent, double sellingPrice) {
        this.beetle = beetle;
        this.container = container;
        this.timeSpent = timeSpent;
        this.sellingPrice = sellingPrice;
    }

    public Beetle getBeetle() {
        return beetle;
    }

    public Container getContainer() {
        return container;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }
}
