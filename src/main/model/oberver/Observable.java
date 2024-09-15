package src.main.model.oberver;

import src.main.model.company.Customer;
import src.main.model.company.Supplier;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<CustomerObserver> customerObservers;
    private List<SupplierObserver> supplierObservers;
    private List<PurchaseObserver> purchaseObservers;

    public Observable() {
        customerObservers = new ArrayList<>();
        supplierObservers = new ArrayList<>();
        purchaseObservers = new ArrayList<>();
    }

    public void addObserver(CustomerObserver o) {
        customerObservers.add(o);
    }

    public void addObserver(SupplierObserver o) { supplierObservers.add(o); }

    public void addObserver(PurchaseObserver o) {
        purchaseObservers.add(o);
    }

    public void removeObserver(CustomerObserver o) {
        customerObservers.remove(o);
    }

    public void removeObserver(PurchaseObserver o) {
        purchaseObservers.remove(o);
    }

    protected void notifyObservers(Customer customer) {
        for (CustomerObserver o : customerObservers) {
            o.update(customer);
        }
    }

    protected void notifyObservers(Supplier supplier) {
        for (SupplierObserver o : supplierObservers) {
            o.update(supplier);
        }
    }

    protected void notifyObservers() {
        for (PurchaseObserver o : purchaseObservers) {
            o.update();
        }
    }

}
