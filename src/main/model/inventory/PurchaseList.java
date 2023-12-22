package src.main.model.inventory;

import org.json.JSONArray;
import org.json.JSONObject;
import src.main.persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class PurchaseList {

    private List<Purchase> purchaseList;

    // EFFECTS: constructs an empty list of purchases
    public PurchaseList() {
        purchaseList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a purchase to this list
    public void addPurchase(Purchase purchase) {
        purchaseList.add(purchase);
    }

    // EFFECTS: return the total amount (in dollars) of purchase in this list
    public double getAmountPurchased() {
        double amount = 0;
        for (Purchase purchase: purchaseList) {
            amount += purchase.getAmountPurchased();
        }
        return amount;
    }

    public int getNumPurchases() {
        return purchaseList.size();
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public boolean isEmpty() {
        return purchaseList.isEmpty();
    }

    public Purchase get(int index) {
        return purchaseList.get(index);
    }

    // EFFECTS: return the purchaseList as a JSON array
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();
        for (Purchase p : purchaseList) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}


