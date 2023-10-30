package src.test.model.company;

import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyerTest extends ModelTest {

    @Test
    void testConstructor() {
        assertEquals("Zenia", testBuyer.getName());
        assertEquals("_zenialau_", testBuyer.getIgAccount());
        //assertTrue(testBuyer.getListOfPurchases().isEmpty());
    }

//    @Test
//    void testAddPurchaseOnce() {
//        testBuyer.addPurchase(testPurchase);
//        assertEquals(1, testBuyer.getNumPurchases());
//        List<Purchase> purchaseList = testBuyer.getListOfPurchases();
//        assertEquals(testPurchase, purchaseList.get(0));
//    }
//    @Test
//    void testAddPurchaseMultiple() {
//        testBuyer.addPurchase(testPurchase);
//        testBuyer.addPurchase(testPurchase);
//        assertEquals(2, testBuyer.getNumPurchases());
//        List<Purchase> purchaseList = testBuyer.getListOfPurchases();
//        assertEquals(testPurchase, purchaseList.get(0));
//        assertEquals(testPurchase, purchaseList.get(1));
//    }
//
//    @Test
//    void testGetAmountPurchasedEmpty() {
//        assertEquals(0, testBuyer.getTotalAmountPurchased());
//    }
//    @Test
//    void testGetAmountPurchasedGeneral() {
//        testPurchase.addItem(testItem);
//        testPurchase.addItem(testItem); // $200 purchase
//        testBuyer.addPurchase(testPurchase);
//        testBuyer.addPurchase(testPurchase); // $200 purchase * 2
//        assertEquals(400, testBuyer.getTotalAmountPurchased());
//    }

}
