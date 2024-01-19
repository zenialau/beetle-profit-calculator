package src.main.model.inventory;

import org.json.JSONArray;
import org.json.JSONObject;
import src.main.persistence.Writable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// represents a one time purchase with 1 or more InventoryItem
public class Purchase implements Writable, Iterable<InventoryItem> {
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

    // EFFECTS: return true is there are no items is this purchase
    public boolean isEmpty() {
        return items.isEmpty();
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("purchaseDate", purchaseDate.toString());
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: return this.items as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (InventoryItem i :items) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: return a String representation of this purchase
    public String toString() {
        return "[" + purchaseDate.toString() + "] " + items.get(0).toString();
    }

    @Override
    public Iterator<InventoryItem> iterator() {
        return items.iterator();
    }
}
