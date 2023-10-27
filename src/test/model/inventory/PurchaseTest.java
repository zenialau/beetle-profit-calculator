package src.test.model.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.company.Buyer;
import src.main.model.inventory.Beetle;
import src.main.model.inventory.Container;
import src.main.model.inventory.MountedDisplay;
import src.main.model.inventory.Purchase;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {
    Purchase testPurchase;
    Beetle testBeetle;
    Container testContainer;
    MountedDisplay testItem;

    @BeforeEach
    void runBefore() {
        testPurchase = new Purchase(new Buyer("Zenia", "_zenialau_"));
        testBeetle = new Beetle("Megasoma gyas", 100, 95);
        testContainer = new Container("frame", "small", 10);
        testItem = new MountedDisplay(testBeetle, testContainer, 60, 100);
    }

    @Test
    void testConstructor() {
        assertTrue(testPurchase.getItems().isEmpty());
    }

    @Test
    void testAddItemOnce() {
        testPurchase.addItem(testItem);
        assertEquals(1, testPurchase.getNumItems());
    }
    @Test
    void testAddItemMultiple() {
        testPurchase.addItem(testItem);
        testPurchase.addItem(testItem);
        assertEquals(2, testPurchase.getNumItems());
    }

    @Test
    void testGetCheckoutAmountEmpty() {
        assertEquals(0, testPurchase.getCheckoutAmount());
    }
    @Test
    void testGetCheckoutAmountOne() {
        testPurchase.addItem(testItem);
        assertEquals(100, testPurchase.getCheckoutAmount());
    }
    @Test
    void testGetCheckoutAmountMultiple() {
        testPurchase.addItem(testItem);
        testPurchase.addItem(testItem);
        assertEquals(200, testPurchase.getCheckoutAmount());
    }
}
