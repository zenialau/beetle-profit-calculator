//package src.test.model.oldinventory;
//
//import org.junit.jupiter.api.Test;
//import src.main.model.oldinventory.OldPurchase;
//import src.test.model.ModelTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class OldOldPurchaseListTest extends ModelTest {
//
//    @Test
//    void testConstructor() {
//        assertTrue(testList.getPurchaseList().isEmpty());
//    }
//
//    @Test
//    void testAddPurchaseOnce() {
//        testList.addPurchase(testPurchase);
//        assertEquals(1, testList.getNumPurchases());
//        List<OldPurchase> purchaseList = testList.getPurchaseList();
//        assertEquals(testPurchase, purchaseList.get(0));
//    }
//    @Test
//    void testAddPurchaseMultiple() {
//        testList.addPurchase(testPurchase);
//        testList.addPurchase(testPurchase);
//        assertEquals(2, testList.getNumPurchases());
//        List<OldPurchase> purchaseList = testList.getPurchaseList();
//        assertEquals(testPurchase, purchaseList.get(0));
//        assertEquals(testPurchase, purchaseList.get(1));
//    }
//
//    @Test
//    void testGetAmountPurchasedEmpty() {
//        assertEquals(0, testList.getAmountPurchased());
//    }
//    @Test
//    void testGetAmountPurchasedGeneral() {
//        testPurchase.addItem(testItem1);
//        testPurchase.addItem(testItem2); // $250 purchase
//        testList.addPurchase(testPurchase);
//        testList.addPurchase(testPurchase); // $250 purchase * 2
//        assertEquals(500, testList.getAmountPurchased());
//    }
//}
