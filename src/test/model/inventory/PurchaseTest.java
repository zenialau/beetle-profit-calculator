package src.test.model.inventory;

import org.junit.jupiter.api.Test;
import src.main.model.inventory.Product;
import src.test.model.ModelTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest extends ModelTest {

    @Test
    void testConstructor() {
        assertTrue(testPurchase.getItems().isEmpty());
    }

    @Test
    void testAddItemOnce() {
        testPurchase.addItem(testItem1);
        assertEquals(1, testPurchase.getNumItems());
    }
    @Test
    void testAddItemMultiple() {
        testPurchase.addItem(testItem1);
        testPurchase.addItem(testItem2);
        assertEquals(2, testPurchase.getNumItems());
        List<Product> items = testPurchase.getItems();
        assertTrue(items.contains(testItem1));
        assertTrue(items.contains(testItem2));
    }

    @Test
    void testGetCheckoutAmountEmpty() {
        assertEquals(0, testPurchase.getCheckoutAmount());
    }
    @Test
    void testGetCheckoutAmountOne() {
        testPurchase.addItem(testItem1);
        assertEquals(100, testPurchase.getCheckoutAmount());
    }
    @Test
    void testGetCheckoutAmountMultiple() {
        testPurchase.addItem(testItem1);
        testPurchase.addItem(testItem2);
        assertEquals(250, testPurchase.getCheckoutAmount());
    }
}
