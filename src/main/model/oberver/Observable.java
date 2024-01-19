package src.main.model.oberver;

import src.main.model.company.Buyer;
import src.main.model.inventory.Purchase;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<Observer> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    protected void notifyObservers(Buyer buyer) {
        for (Observer o : observers) {
            o.update(buyer);
        }
    }

    protected void notifyObservers(Purchase purchase) {
        for (Observer o : observers) {
            o.update(purchase);
        }
    }

}
