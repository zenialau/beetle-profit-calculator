package src.test.model.inventory;

import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testSetDateCorrectFormat() {
        testPurchase1.setPurchaseDate("2004-10-21");
        assertEquals("2004-10-21", testPurchase1.getPurchaseDate().toString());
    }

    @Test
    void testSetDateWrongFormat() {
        try {
            testPurchase1.setPurchaseDate("slkdfjlkj");
            fail("DateTimeParseException expected but not thrown. ");
        } catch (DateTimeParseException e) {
            // expected
        }
    }

    @Test
    void testSetDateBlank() {
        try {
            testPurchase1.setPurchaseDate("");
            fail("DateTimeParseException expected but not thrown. ");
        } catch (DateTimeParseException e) {
            // expected
        }

        try {
            testPurchase1.setPurchaseDate("   ");
            fail("DateTimeParseException expected but not thrown. ");
        } catch (DateTimeParseException e) {
            // expected
        }
    }

}
