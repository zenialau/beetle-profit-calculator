package src.main.model.company;

import src.main.model.exception.DuplicateException;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Represent a map with key: Buyer and value: PurchaseList
public class BuyersMap {
    private Map<Buyer, PurchaseList> buyersMap;

    // EFFECTS: constructs an empty map of buyers
    public BuyersMap() {
        buyersMap = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: if buyer already exists in the map, throw DuplicateException
    //          else add buyer to buyersMap
    public void addBuyer(Buyer buyer) throws DuplicateException {
        if (getListOfBuyers().contains(buyer)) {
            throw new DuplicateException();
        } else {
            buyersMap.put(buyer, new PurchaseList());
        }
    }

    // REQUIRES: buyer exists in the map
    // MODIFIES: this
    // EFFECTS: add the purchase to the PurchaseList of the specified buyer
    public void addPurchase(Buyer buyer, Purchase purchase) {
        PurchaseList pl = getPurchaseList(buyer);
        pl.addPurchase(purchase);
        buyersMap.put(buyer, pl);
    }

    // EFFECTS: return the PurchaseList of the specified buyer
    public PurchaseList getPurchaseList(Buyer buyer) {
        return buyersMap.get(buyer);
    }

    // EFFECTS: return the total amount made in purchases by the specified buyer
    public double getAmountPurchased(Buyer buyer) {
        return getPurchaseList(buyer).getAmountPurchased();
    }

    // EFFECTS: return the total amount earned all buyers in Map
    public double getTotalRevenue() {
        double total = 0;
        for (Buyer buyer : getListOfBuyers()) {
            total += getAmountPurchased(buyer);
        }
        return total;
    }

    public Set<Buyer> getListOfBuyers() {
        return buyersMap.keySet();
    }

    public Map<Buyer, PurchaseList> getBuyersMap() {
        return buyersMap;
    }

}
