package src.main.model.oberver;


import src.main.model.company.Buyer;
import src.main.model.inventory.Purchase;

public interface Observer {

    void update(Buyer buyer);

    void update(Purchase purchase);

}
