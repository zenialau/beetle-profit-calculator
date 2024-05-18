package src.main.model.oberver;

import src.main.model.company.Buyer;
import src.main.model.inventory.Purchase;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<BuyerObserver> buyerObservers;
    private List<SupplierObserver> supplierObservers;
    private List<PurchaseObserver> purchaseObservers;

    public Observable() {
        buyerObservers = new ArrayList<>();
        purchaseObservers = new ArrayList<>();
    }

    public void addObserver(BuyerObserver o) {
        buyerObservers.add(o);
    }

    public void addObserver(SupplierObserver o) { supplierObservers.add(o); }

    public void addObserver(PurchaseObserver o) {
        purchaseObservers.add(o);
    }

    public void removeObserver(BuyerObserver o) {
        buyerObservers.remove(o);
    }

    public void removeObserver(PurchaseObserver o) {
        purchaseObservers.remove(o);
    }

    protected void notifyObservers(Buyer buyer) {
        for (BuyerObserver o : buyerObservers) {
            o.update(buyer);
        }
    }

    protected void notifyObservers() {
        for (PurchaseObserver o : purchaseObservers) {
            o.update();
        }
    }

}
