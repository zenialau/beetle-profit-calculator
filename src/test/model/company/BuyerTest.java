package src.test.model.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.company.Buyer;
import src.main.model.inventory.Beetle;
import src.main.model.inventory.Container;
import src.main.model.inventory.MountedDisplay;
import src.main.model.inventory.Purchase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuyerTest {
    Buyer testBuyer;
    Purchase testPurchase;
    Beetle testBeetle;
    Container testContainer;
    MountedDisplay testItem;

    @BeforeEach
    void runBefore() {
        testBuyer = new Buyer("Zenia", "_zenialau_");
        testBeetle = new Beetle("Megasoma gyas", 100, 95);
        testContainer = new Container("frame", "small", 10);
        testItem = new MountedDisplay(testBeetle, testContainer, 60, 100);
        testPurchase = new Purchase(testBuyer);
    }

    @Test
    void testConstructor() {
        assertEquals("Zenia", testBuyer.getName());
        assertEquals("_zenialau_", testBuyer.getIgAccount());
        assertTrue(testBuyer.getListOfPurchases().isEmpty());
    }

    @Test
    void testAddPurchaseOnce() {
        testBuyer.addPurchase(testPurchase);
        assertEquals(1, testBuyer.getNumPurchases());
        List<Purchase> purchaseList = testBuyer.getListOfPurchases();
        assertEquals(testPurchase, purchaseList.get(0));
    }
    @Test
    void testAddPurchaseMultiple() {
        testBuyer.addPurchase(testPurchase);
        testBuyer.addPurchase(testPurchase);
        assertEquals(2, testBuyer.getNumPurchases());
        List<Purchase> purchaseList = testBuyer.getListOfPurchases();
        assertEquals(testPurchase, purchaseList.get(0));
        assertEquals(testPurchase, purchaseList.get(1));
    }

    @Test
    void testGetAmountPurchasedEmpty() {
        assertEquals(0, testBuyer.getAmountPurchased());
    }
    @Test
    void testGetAmountPurchasedGeneral() {
        testPurchase.addItem(testItem);
        testPurchase.addItem(testItem); // $200 purchase
        testBuyer.addPurchase(testPurchase);
        testBuyer.addPurchase(testPurchase); // $200 purchase * 2
        assertEquals(400, testBuyer.getAmountPurchased());
    }

}
