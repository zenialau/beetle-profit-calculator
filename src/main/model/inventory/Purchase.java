package src.main.model.inventory;

import java.util.ArrayList;
import java.util.List;

// Represents a purchase by a Buyer
public class Purchase {
    List<Product> items;

    // EFFECTS: constructs an empty cart of items that belongs to a buyer
    public Purchase() {
        items = new ArrayList<>();
    }

    // REQUIRES: item != null
    // MODIFIES: this
    // EFFECTS: adds an item (mounted display) to "cart"
    public void addItem(Product item) {
        items.add(item);
    }

    // EFFECTS: return the total checkout amount of this purchase (in dollars)
    public double getCheckoutAmount() {
        double amount = 0;
        for (Product item: items) {
            amount += item.getSellingPrice();
        }
        return amount;
    }

    public List<Product> getItems() {
        return items;
    }

    public int getNumItems() {
        return items.size();
    }
}
