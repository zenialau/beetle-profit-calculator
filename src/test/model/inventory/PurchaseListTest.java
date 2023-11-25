package src.test.model.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseListTest {
    PurchaseList testList;
    Purchase testPurchase1;
    Purchase testPurchase2;
    InventoryItem i1;
    InventoryItem i2;

    @BeforeEach
    void runBefore() {
        testList = new PurchaseList();
        testPurchase1 = new Purchase();
        testPurchase2 = new Purchase();
        i1 = new InventoryItem("beetle", "", 95,
                "A1", "", 50);
        i2 = new InventoryItem("container", "", 0,
                "", "10 containers", 10);
        testPurchase1.addItem(i1);
        testPurchase2.addItem(i2);
    }

    @Test
    void testConstructor() {
        assertTrue(testList.isEmpty());
        assertEquals(0, testList.getAmountPurchased());
    }

    @Test
    void testAddPurchaseOnce() {
        testList.addPurchase(testPurchase1);
        assertEquals(1, testList.getNumPurchases());
        assertEquals(testPurchase1, testList.get(0));
        assertEquals(50, testList.getAmountPurchased());
    }
    @Test
    void testAddPurchaseMultiple() {
        testList.addPurchase(testPurchase1);
        testList.addPurchase(testPurchase2);
        assertEquals(2, testList.getNumPurchases());
        assertEquals(testPurchase1, testList.get(0));
        assertEquals(testPurchase2, testList.get(1));
        assertEquals(60, testList.getAmountPurchased());
    }

}
