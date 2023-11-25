package src.test.model.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseTest {
    Purchase testPurchase;
    InventoryItem i1;
    InventoryItem i2;

    @BeforeEach
    void runBefore() {
        testPurchase = new Purchase();
        i1 = new InventoryItem("beetle", "", 95,
                "A1", "", 50);
        i2 = new InventoryItem("container", "", 0,
                "", "10 containers", 10);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testPurchase.getNumItems());
        assertEquals(0, testPurchase.getAmountPurchased());
    }

    @Test
    void testAddItemOnce() {
        testPurchase.addItem(i1);
        assertEquals(1, testPurchase.getNumItems());
        assertEquals(50, testPurchase.getAmountPurchased());
        assertTrue(testPurchase.getItems().contains(i1));
    }

    @Test
    void testAddItemMultiple() {
        testPurchase.addItem(i1);
        testPurchase.addItem(i2);
        assertEquals(2, testPurchase.getNumItems());
        assertEquals(60, testPurchase.getAmountPurchased());
        assertTrue(testPurchase.getItems().contains(i1));
        assertTrue(testPurchase.getItems().contains(i2));
    }

}
