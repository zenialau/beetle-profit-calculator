package src.main.model.inventory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// represents a one time purchase with 1 or more InventoryItem
public class Purchase {
    private LocalDate purchaseDate;
    private List<InventoryItem> items;

    // EFFECTS: constructs an empty cart with no items
    public Purchase() {
        items = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add an item to list of items
    public void addItem(InventoryItem item) {
        items.add(item);
    }

    // EFFECTS: return the total amount purchased (in CAD)
    public double getAmountPurchased() {
        double total = 0;
        for (InventoryItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // EFFECTS: return the number of items in this purchase
    public int getNumItems() {
        return items.size();
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    // REQUIRES: String date format: "YYYY-MM-DD"
    public void setPurchaseDate(String date) {
        purchaseDate = LocalDate.parse(date);
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

}
