package src.main.model.company;

import src.main.model.inventory.PurchaseList;
import src.main.persistence.Writable;

public abstract class TradersMap implements Writable {

    public abstract PurchaseList getPurchaseList(Trader trader);
}
