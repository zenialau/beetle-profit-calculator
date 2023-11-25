//package src.main.model.oldinventory;
//
//// Represent a mounted display, with beetle, container, and time spent on it, and selling price
//public class MountedDisplay extends Product{
//    private Beetle beetle;
//    private Container container;
//    private int timeSpent;
//
//    // Constructs a mounted display for sale with a beetle, container, time spent (mins)
//    // and its selling price, set its cost as the sum of the cost of the beetle and container
//    public MountedDisplay(Beetle beetle, Container container, int timeSpent,
//                             double sellingPrice) {
//        super(beetle.getCost() + container.getCost(), sellingPrice);
//        this.beetle = beetle;
//        this.container = container;
//        this.timeSpent = timeSpent;
//    }
//
//    public Beetle getBeetle() {
//        return beetle;
//    }
//
//    public Container getContainer() {
//        return container;
//    }
//
//    public int getTimeSpent() {
//        return timeSpent;
//    }
//
//}
