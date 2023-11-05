package src.test.model.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.company.PurchaseList;
import src.main.model.exception.DuplicateBuyerException;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.*;

public class BuyersMapTest extends ModelTest {
    BuyersMap testMap;

    @BeforeEach
    void setup() {
        testMap = new BuyersMap();
        testPurchase.addItem(testBeetle);
        testPurchase.addItem(testDisplay);
    }

    @Test
    void testConstructor() {
        assertTrue(testMap.getBuyersAndPurchases().isEmpty());
        assertEquals(0, testMap.getTotalRevenue());
    }

    @Test
    void testAddBuyerOnce() {
        try {
            testMap.addBuyer(testBuyer);
            assertEquals(1, testMap.getBuyersAndPurchases().size());
            assertEquals(0, testMap.getNumPurchases(testBuyer));
            assertTrue(testMap.getPurchaseList(testBuyer).isEmpty());
        } catch (DuplicateBuyerException e) {
            fail("DuplicateBuyerException thrown but not expected.");
        }

    }
    @Test
    void testAddBuyerDuplicate() {
        try {
            testMap.addBuyer(testBuyer);
            testMap.addBuyer(testBuyer);
            fail("DuplicateBuyerException expected but not thrown.");
        } catch (DuplicateBuyerException e) {
            // expected
        }
    }
    @Test
    void testAddBuyerDuplicateNew() {
        try {
            testMap.addBuyer(testBuyer);
            testMap.addBuyer(new Buyer("Zenia", "_zenialau_"));
            fail("DuplicateBuyerException expected but not thrown.");
        } catch (DuplicateBuyerException e) {
            // expected
        }
    }

    @Test
    void testAddPurchaseOnce() {
        try {
            testMap.addBuyer(testBuyer);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testMap.addPurchase(testBuyer, testPurchase);
        assertEquals(1, testMap.getBuyersAndPurchases().size());
        assertEquals(1, testMap.getNumPurchases(testBuyer));
        PurchaseList purchases = testMap.getPurchaseList(testBuyer);
        assertEquals(testPurchase, purchases.get(0));
    }
    @Test
    void testAddPurchaseMultiple() {
        try {
            testMap.addBuyer(testBuyer);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testMap.addPurchase(testBuyer, testPurchase);
        testMap.addPurchase(testBuyer, testPurchase);
        assertEquals(1, testMap.getBuyersAndPurchases().size());
        assertEquals(2, testMap.getNumPurchases(testBuyer));
        PurchaseList purchases = testMap.getPurchaseList(testBuyer);
        assertEquals(testPurchase, purchases.get(0));
        assertEquals(testPurchase, purchases.get(1));
    }

    @Test
    void testGetTotalRevenueEmpty() {
        assertEquals(0, testMap.getTotalRevenue());
    }
    @Test
    void testGetTotalRevenueGeneral() {
        try {
            testMap.addBuyer(testBuyer);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testMap.addPurchase(testBuyer, testPurchase);
        assertEquals(250, testMap.getTotalRevenue());
        assertEquals(250, testMap.getRevenueFromBuyer(testBuyer));
        testMap.addPurchase(testBuyer, testPurchase);
        assertEquals(500, testMap.getTotalRevenue());
        assertEquals(500, testMap.getRevenueFromBuyer(testBuyer));
    }

}
