package src.test.model;

import org.junit.jupiter.api.BeforeEach;
import src.main.model.company.*;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;


// set up test objects @BeforeEach
public abstract class ModelTest {
    protected PurchaseList testList;
    protected Purchase testPurchase1;
    protected Purchase testPurchase2;
    protected InventoryItem i1;
    protected InventoryItem i2;
    protected Buyer testBuyer;
    protected Supplier testSupplier;

    @BeforeEach
    void runFirst() {
        testList = new PurchaseList();
        testPurchase1 = new Purchase();
        testPurchase2 = new Purchase();
        i1 = new InventoryItem("beetle", "", 95,
                "A1", "", 50);
        i2 = new InventoryItem("container", "", 0,
                "", "10 containers", 10);
        testBuyer = new Buyer("Zenia", "_zenialau_", "Coquitlam");
        testSupplier = new Supplier("Nigel", "Canada");

    }
}
