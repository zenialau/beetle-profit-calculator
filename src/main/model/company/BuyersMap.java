package src.main.model.company;

import org.json.JSONArray;
import org.json.JSONObject;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Represent a map with key: Buyer and value: PurchaseList
public class BuyersMap extends TradersMap implements Iterable<Buyer> {
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
            notifyObservers(buyer);
        }
    }

    // REQUIRES: buyer exists in the map
    // MODIFIES: this
    // EFFECTS: add the purchase to the PurchaseList of the specified buyer
    public void addPurchase(Buyer buyer, Purchase purchase) {
        PurchaseList pl = getPurchaseList(buyer);
        pl.addPurchase(purchase);
        buyersMap.put(buyer, pl);
        notifyObservers(purchase);
    }

    // EFFECTS: return the PurchaseList of the specified buyer
    @Override
    public PurchaseList getPurchaseList(Trader buyer) {
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

    // EFFECTS: return the most recent purchase made by the specified buyer
    public Purchase getLastPurchase(Buyer buyer) {
        PurchaseList pl = this.getPurchaseList(buyer);
        return pl.getLastPurchase();
    }

    public Set<Buyer> getListOfBuyers() {
        return buyersMap.keySet();
    }

    public Map<Buyer, PurchaseList> getBuyersMap() {
        return buyersMap;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("buyers", buyersToJson());
        return json;
    }

    // EFFECTS: returns buyers in this.buyersMap as a JSON array
    private JSONArray buyersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Buyer b : getListOfBuyers()) {
            jsonArray.put(buyerToJson(b));
        }
        return jsonArray;
    }

    // EFFECTS: returns Buyer with corresponding PurchaseList as a JSON object
    private JSONObject buyerToJson(Buyer buyer) {
        JSONObject json;
        json = buyer.toJson();
        json.put("purchaseList", this.getPurchaseList(buyer).toJson());
        return json;
    }

    @Override
    public Iterator<Buyer> iterator() {
        return buyersMap.keySet().iterator();
    }

}
