package src.main.model.company;

import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.model.oberver.Observable;
import src.main.persistence.Writable;

public abstract class TradersMap extends Observable implements Writable {

    public abstract PurchaseList getPurchaseList(Trader trader);
    public abstract void addPurchase(Trader trader, Purchase purchase);
}
