package src.main.model.company;

import src.main.model.oldinventory.PurchaseList;

import java.util.HashMap;
import java.util.Map;

// Represent a map with key: Buyer and value: list of
public class BuyersMap {
    private Map<Buyer, PurchaseList> buyersMap; // give super method as parameter?

    // EFFECTS: constructs an empty map of buyers
    public BuyersMap() {
        buyersMap = new HashMap<>();
    }



}
