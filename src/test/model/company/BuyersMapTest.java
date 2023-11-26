package src.test.model.company;

import jdk.jshell.spi.ExecutionControlProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.company.Supplier;
import src.main.model.exception.DuplicateException;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.*;

public class BuyersMapTest extends ModelTest {
    BuyersMap testBuyersMap;
    Buyer testBuyer1Same;
    Buyer testBuyer2;


    @BeforeEach
    void setUpBuyers() {
        testBuyersMap = new BuyersMap();
        testBuyer1Same = new Buyer("Zenia", "_zenialau_", "Canada");
        testBuyer2 = new Buyer("Katherine", "katherinelim", "Richmond");
    }

    @Test
    void testConstructor() {
        assertTrue(testBuyersMap.getBuyersMap().isEmpty());
        assertEquals(0, testBuyersMap.getTotalRevenue());
    }

    @Test
    void testAddBuyerOnce() {
        try {
            testBuyersMap.addBuyer(testBuyer1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        assertEquals(1, testBuyersMap.getListOfBuyers().size());
        assertEquals(0, testBuyersMap.getAmountPurchased(testBuyer1));
    }
    @Test
    void testAddBuyerMultiple() {
        try {
            testBuyersMap.addBuyer(testBuyer1);
            testBuyersMap.addBuyer(testBuyer2);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        assertEquals(2, testBuyersMap.getListOfBuyers().size());
        assertEquals(0, testBuyersMap.getAmountPurchased(testBuyer1));
        assertEquals(0, testBuyersMap.getAmountPurchased(testBuyer2));
    }
    @Test
    void testAddBuyerDuplicate() {
        try {
            testBuyersMap.addBuyer(testBuyer1);
            testBuyersMap.addBuyer(testBuyer1Same);
            fail("DuplicateException expected but not thrown.");
        } catch (DuplicateException e) {
            // expected
        }
        assertEquals(1, testBuyersMap.getListOfBuyers().size());
        assertTrue(testBuyersMap.getListOfBuyers().contains(testBuyer1));
        assertEquals(0, testBuyersMap.getAmountPurchased(testBuyer1));
    }

    @Test
    void testAddPurchaseOnce() {
        try {
            testBuyersMap.addBuyer(testBuyer1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testPurchase1.addItem(i1); // $50
        testBuyersMap.addPurchase(testBuyer1, testPurchase1);
        assertEquals(50, testBuyersMap.getAmountPurchased(testBuyer1));
        assertEquals(50, testBuyersMap.getTotalRevenue());
    }
    @Test
    void testAddPurchaseMultipleSameBuyer() {
        try {
            testBuyersMap.addBuyer(testBuyer1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testPurchase1.addItem(i1); // $50
        testPurchase2.addItem(i2); // 10
        testBuyersMap.addPurchase(testBuyer1, testPurchase1);
        testBuyersMap.addPurchase(testBuyer1, testPurchase2);
        assertEquals(60, testBuyersMap.getAmountPurchased(testBuyer1));
        assertEquals(60, testBuyersMap.getTotalRevenue());
    }
    @Test
    void testAddPurchaseMultipleDiffBuyer() {
        try {
            testBuyersMap.addBuyer(testBuyer1);
            testBuyersMap.addBuyer(testBuyer2);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testPurchase1.addItem(i1); // $50
        testPurchase2.addItem(i2); // 10
        testBuyersMap.addPurchase(testBuyer1, testPurchase1);
        testBuyersMap.addPurchase(testBuyer2, testPurchase2);
        assertEquals(50, testBuyersMap.getAmountPurchased(testBuyer1));
        assertEquals(10, testBuyersMap.getAmountPurchased(testBuyer2));
        assertEquals(60, testBuyersMap.getTotalRevenue());
    }

}
