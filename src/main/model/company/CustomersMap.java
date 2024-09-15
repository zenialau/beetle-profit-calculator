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
public class CustomersMap extends TradersMap implements Iterable<Customer> {
    private Map<Customer, PurchaseList> customersMap;

    // EFFECTS: constructs an empty map of customers
    public CustomersMap() {
        customersMap = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: if buyer already exists in the map, throw DuplicateException
    //          else add buyer to customersMap
    public void addBuyer(Customer customer) throws DuplicateException {
        if (getListOfCustomers().contains(customer)) {
            throw new DuplicateException();
        } else {
            customersMap.put(customer, new PurchaseList());
            notifyObservers(customer);
        }
    }

    // REQUIRES: customer exists in the map
    // MODIFIES: this
    // EFFECTS: add the purchase to the PurchaseList of the specified customer
    @Override
    public void addPurchase(Trader customer, Purchase purchase) {
        PurchaseList pl = getPurchaseList(customer);
        pl.addPurchase(purchase);
        customersMap.put((Customer) customer, pl);
        notifyObservers();
    }

    // EFFECTS: return the PurchaseList of the specified buyer
    @Override
    public PurchaseList getPurchaseList(Trader buyer) {
        return customersMap.get(buyer);
    }

    // EFFECTS: return the total amount made in purchases by the specified buyer
    public double getAmountPurchased(Customer customer) {
        return getPurchaseList(customer).getAmountPurchased();
    }

    // EFFECTS: return the total amount earned all customers in Map
    public double getTotalRevenue() {
        double total = 0;
        for (Customer customer : getListOfCustomers()) {
            total += getAmountPurchased(customer);
        }
        return total;
    }

    // EFFECTS: return the most recent purchase made by the specified buyer
    public Purchase getLastPurchase(Customer customer) {
        PurchaseList pl = this.getPurchaseList(customer);
        return pl.getLastPurchase();
    }

    public Set<Customer> getListOfCustomers() {
        return customersMap.keySet();
    }

    public Map<Customer, PurchaseList> getCustomersMap() {
        return customersMap;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("customers", customersToJson());
        return json;
    }

    // EFFECTS: returns customers in this.customersMap as a JSON array
    private JSONArray customersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Customer b : getListOfCustomers()) {
            jsonArray.put(customerToJson(b));
        }
        return jsonArray;
    }

    // EFFECTS: returns Buyer with corresponding PurchaseList as a JSON object
    private JSONObject customerToJson(Customer customer) {
        JSONObject json;
        json = customer.toJson();
        json.put("purchaseList", this.getPurchaseList(customer).toJson());
        return json;
    }

    @Override
    public Iterator<Customer> iterator() {
        return customersMap.keySet().iterator();
    }

}
