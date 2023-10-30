package src.main.model.company;

// if I use this, i prob dont need to keep track of list of purchases in Buyer or buyer in Purchase
// when i want to add a purchase, i could use the Buyer info (key) to get the value (list of
// purchases) and add a Purchase to that object. but this would mean that i do need a
// ListOfPurchase class.

import java.util.Map;

// Represent a map with key: Buyer and value: list of
public class BuyersMap {
    Map buyers;

    // EFFECTS: constructs an empty map of buyers
    //          key = Buyer
    //          value = ListOfPurchase
    public BuyersMap() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: use the buyer key to find their list of purchases and add a purchase to it
    public void addPurchase(Buyer buyer, Purchase purchase) {
        //stub
    }

    // EFFECTS: return the total amount (in dollars) of revenue made by selling
    public double getTotalRevenue() {
        return 0; //stub
    }

    // EFFECTS: return the number of purchases made by the specified buyer
    public int getNumPurchases(Buyer buyer) {
        return -1; //stub
    }

    // REQUIRES: buyer != null
    // EFFECTS: return the specified buyers' purchase list
    public PurchaseList getPurchaseList(Buyer buyer) {
        return null; //stub
    }

    public Map getBuyersAndPurchases() {
        return buyers;
    }
}
