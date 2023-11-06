package src.test.model.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.company.PurchaseList;
import src.main.model.company.TradersMap;
import src.main.model.exception.DuplicateBuyerException;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.*;

public class TradersMapTest extends ModelTest {
    TradersMap testMap;

    @BeforeEach
    void setup() {
        testMap = new BuyersMap();
        testPurchase.addItem(testBeetle);
        testPurchase.addItem(testDisplay);
    }

    @Test
    void testConstructor() {
        assertTrue(testMap.getTradersAndPurchases().isEmpty());
        assertEquals(0, testMap.getTotalRevenueOrExpense());
    }

    @Test
    void testAddBuyerOnce() {
        try {
            testMap.addTrader(testBuyer);
            assertEquals(1, testMap.getTradersAndPurchases().size());
            assertEquals(0, testMap.getNumPurchases(testBuyer));
            assertTrue(testMap.getPurchaseList(testBuyer).isEmpty());
        } catch (DuplicateBuyerException e) {
            fail("DuplicateBuyerException thrown but not expected.");
        }

    }
    @Test
    void testAddBuyerDuplicate() {
        try {
            testMap.addTrader(testBuyer);
            testMap.addTrader(testBuyer);
            fail("DuplicateBuyerException expected but not thrown.");
        } catch (DuplicateBuyerException e) {
            // expected
        }
    }
    @Test
    void testAddBuyerDuplicateNew() {
        try {
            testMap.addTrader(testBuyer);
            testMap.addTrader(new Buyer("Zenia", "_zenialau_", "Coquitlam"));
            fail("DuplicateBuyerException expected but not thrown.");
        } catch (DuplicateBuyerException e) {
            // expected
        }
    }

    @Test
    void testAddPurchaseOnce() {
        try {
            testMap.addTrader(testBuyer);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testMap.addPurchase(testBuyer, testPurchase);
        assertEquals(1, testMap.getTradersAndPurchases().size());
        assertEquals(1, testMap.getNumPurchases(testBuyer));
        PurchaseList purchases = testMap.getPurchaseList(testBuyer);
        assertEquals(testPurchase, purchases.get(0));
    }
    @Test
    void testAddPurchaseMultiple() {
        try {
            testMap.addTrader(testBuyer);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testMap.addPurchase(testBuyer, testPurchase);
        testMap.addPurchase(testBuyer, testPurchase);
        assertEquals(1, testMap.getTradersAndPurchases().size());
        assertEquals(2, testMap.getNumPurchases(testBuyer));
        PurchaseList purchases = testMap.getPurchaseList(testBuyer);
        assertEquals(testPurchase, purchases.get(0));
        assertEquals(testPurchase, purchases.get(1));
    }

    @Test
    void testGetTotalRevenueEmpty() {
        assertEquals(0, testMap.getTotalRevenueOrExpense());
    }
    @Test
    void testGetTotalRevenueGeneral() {
        try {
            testMap.addTrader(testBuyer);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testMap.addPurchase(testBuyer, testPurchase);
        assertEquals(250, testMap.getTotalRevenueOrExpense());
        assertEquals(250, testMap.getAmountFromTrader(testBuyer));
        testMap.addPurchase(testBuyer, testPurchase);
        assertEquals(500, testMap.getTotalRevenueOrExpense());
        assertEquals(500, testMap.getAmountFromTrader(testBuyer));
    }
}
