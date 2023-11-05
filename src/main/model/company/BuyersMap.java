package src.main.model.company;

// if I use this, i prob dont need to keep track of list of purchases in Buyer or buyer in Purchase
// when i want to add a purchase, i could use the Buyer info (key) to get the value (list of
// purchases) and add a Purchase to that object. but this would mean that i do need a
// ListOfPurchase class.

import src.main.model.exception.DuplicateBuyerException;

import java.util.*;

// Represent a map with key: Buyer and value: list of
public class BuyersMap {
    private Map<Buyer, PurchaseList> buyers;

    // EFFECTS: constructs an empty map of buyers
    //          key = Buyer
    //          value = PurchaseList
    public BuyersMap() {
        buyers = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: add a Buyer and an empty PurchaseList to the map
    //          if the same buyer already exist, throw DuplicateBuyerException
    public void addBuyer(Buyer buyer) throws DuplicateBuyerException {
        if (buyers.containsKey(buyer)) {
            throw new DuplicateBuyerException();
        }
        buyers.put(buyer, new PurchaseList());
    }

    // REQUIRES: an existing buyer in map
    // MODIFIES: this
    // EFFECTS: use the buyer key to find their list of purchases and add a purchase to it
    public void addPurchase(Buyer buyer, Purchase purchase) {
        PurchaseList list = buyers.get(buyer);
        list.addPurchase(purchase);
    }

    // EFFECTS: return the total amount (in dollars) of revenue made by selling
    public double getTotalRevenue() {
        double total = 0;
        for (Map.Entry<Buyer, PurchaseList> entry : buyers.entrySet()) {
            PurchaseList buyerList = entry.getValue();
            total += buyerList.getAmountPurchased();
        }
        return total;
    }

    public double getRevenueFromBuyer(Buyer buyer) {
        PurchaseList buyerList = buyers.get(buyer);
        return buyerList.getAmountPurchased();
    }

    // EFFECTS: return the number of purchases made by the specified buyer
    public int getNumPurchases(Buyer buyer) {
        return buyers.get(buyer).getNumPurchases();
    }

    // REQUIRES: buyer != null
    // EFFECTS: return the specified buyers' purchase list
    public PurchaseList getPurchaseList(Buyer buyer) {
        return buyers.get(buyer);
    }

    public Map getBuyersAndPurchases() {
        return buyers;
    }
}
