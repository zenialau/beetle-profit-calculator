package src.main.model.company;

import src.main.model.exception.DuplicateBuyerException;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;

import java.util.HashMap;
import java.util.Map;

// Represents a Trader(buyer/supplier) map with key: Trader and value: PurchaseList
public class TradersMap {
    protected Map<Trader, PurchaseList> tradersMap;

    // EFFECTS: constructs an empty map of traders
    //          key = Trader
    //          value = PurchaseList
    public TradersMap() {
        tradersMap = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: add a Trader and an empty PurchaseList to the map
    //          if the same trader already exist, throw DuplicateBuyerException
    public void addTrader(Trader trader) throws DuplicateBuyerException {
        if (tradersMap.containsKey(trader)) {
            throw new DuplicateBuyerException();
        }
        tradersMap.put(trader, new PurchaseList());
    }

    // REQUIRES: an existing trader in map
    // MODIFIES: this
    // EFFECTS: use the trader key to find their list of purchases and add a purchase to it
    public void addPurchase(Trader trader, Purchase purchase) {
        PurchaseList list = tradersMap.get(trader);
        list.addPurchase(purchase);
    }

    // EFFECTS: return the total amount (in dollars) of revenue/expense from selling/buying
    public double getTotalRevenueOrExpense() {
        double total = 0;
        for (Map.Entry<Trader, PurchaseList> entry : tradersMap.entrySet()) {
            PurchaseList traderList = entry.getValue();
            total += traderList.getAmountPurchased();
        }
        return total;
    }

    // EFFECTS: return the total amount (in dollars) of revenue/expense from the specific trader
    public double getAmountFromTrader(Trader trader) {
        PurchaseList traderList = tradersMap.get(trader);
        return traderList.getAmountPurchased();
    }

    // EFFECTS: return the number of purchases made by the specified trader
    public int getNumPurchases(Trader trader) {
        return tradersMap.get(trader).getNumPurchases();
    }

    // REQUIRES: trader != null
    // EFFECTS: return the specified traders' purchase list
    public PurchaseList getPurchaseList(Trader trader) {
        return tradersMap.get(trader);
    }

    public Map<Trader, PurchaseList> getTradersAndPurchases() {
        return tradersMap;
    }

}
