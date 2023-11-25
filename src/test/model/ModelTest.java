package src.test.model;

import org.junit.jupiter.api.BeforeEach;
import src.main.model.company.*;


// set up test objects @BeforeEach
public abstract class ModelTest {
//    protected Beetle testBeetle;
//    protected Container testContainer;
//    protected MountedDisplay testDisplay;
//    protected Product testItem1;
//    protected Product testItem2;
//    protected OldPurchase testPurchase;
//    protected OldPurchaseList testList;
    protected Trader testTraderBuyer;
    protected Trader testTraderSupplier;
    protected Buyer testBuyer;
    protected Supplier testSupplier;

    @BeforeEach
    void runBefore() {
//        testBeetle = new Beetle("Megasoma gyas", 95, 50, 100);
//        testContainer = new Container("frame", "small", 10);
//        testDisplay = new MountedDisplay(testBeetle, testContainer, 60, 150);
//        testItem1 = testBeetle;
//        testItem2 = testDisplay;
//        testPurchase = new OldPurchase();
//        testList = new OldPurchaseList();
        testBuyer = new Buyer("Zenia", "_zenialau_", "Coquitlam");
        testSupplier = new Supplier("Nigel", "Canada");
        testTraderBuyer = testBuyer;
        testTraderSupplier = testSupplier;
    }
}
