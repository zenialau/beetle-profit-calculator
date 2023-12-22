package src.main.model.company;

import org.json.JSONObject;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Represents a map with key: Supplier, value: PurchaseList
public class SuppliersMap extends TradersMap implements Iterable<Supplier> {
    private Map<Supplier, PurchaseList> suppliersMap;

    // EFFECTS: constructs an empty map of buyers
    public SuppliersMap() {
        suppliersMap = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: if buyer already exists in the map, throw DuplicateException
    //          else add buyer to buyersMap
    public void addSupplier(Supplier supplier) throws DuplicateException {
        if (getListOfSuppliers().contains(supplier)) {
            throw new DuplicateException();
        } else {
            suppliersMap.put(supplier, new PurchaseList());
        }
    }

    // REQUIRES: buyer exists in the map
    // MODIFIES: this
    // EFFECTS: add the purchase to the PurchaseList of the specified buyer
    public void addPurchase(Supplier supplier, Purchase purchase) {
        PurchaseList pl = getPurchaseList(supplier);
        pl.addPurchase(purchase);
        suppliersMap.put(supplier, pl);
    }

    // EFFECTS: return the PurchaseList of the specified buyer
    @Override
    public PurchaseList getPurchaseList(Trader supplier) {
        return suppliersMap.get(supplier);
    }

    // EFFECTS: return the total amount made in purchases by the specified buyer
    public double getAmountPurchased(Supplier supplier) {
        return getPurchaseList(supplier).getAmountPurchased();
    }

    // EFFECTS: return the total amount earned all buyers in Map
    public double getTotalExpense() {
        double total = 0;
        for (Supplier supplier : getListOfSuppliers()) {
            total += getAmountPurchased(supplier);
        }
        return total;
    }

    public Set<Supplier> getListOfSuppliers() {
        return suppliersMap.keySet();
    }

    public Map<Supplier, PurchaseList> getSuppliersMap() {
        return suppliersMap;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }

    @Override
    public Iterator<Supplier> iterator() {
        return suppliersMap.keySet().iterator();
    }
}
