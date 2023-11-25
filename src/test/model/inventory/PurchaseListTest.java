package src.test.model.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseListTest extends ModelTest {

    @BeforeEach
    void runBefore() {
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
