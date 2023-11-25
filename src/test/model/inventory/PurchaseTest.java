package src.test.model.inventory;

import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PurchaseTest extends ModelTest {

    @Test
    void testConstructor() {
        assertEquals(0, testPurchase1.getNumItems());
        assertEquals(0, testPurchase1.getAmountPurchased());
    }

    @Test
    void testAddItemOnce() {
        testPurchase1.addItem(i1);
        assertEquals(1, testPurchase1.getNumItems());
        assertEquals(50, testPurchase1.getAmountPurchased());
        assertTrue(testPurchase1.getItems().contains(i1));
    }

    @Test
    void testAddItemMultiple() {
        testPurchase1.addItem(i1);
        testPurchase1.addItem(i2);
        assertEquals(2, testPurchase1.getNumItems());
        assertEquals(60, testPurchase1.getAmountPurchased());
        assertTrue(testPurchase1.getItems().contains(i1));
        assertTrue(testPurchase1.getItems().contains(i2));
    }

}
