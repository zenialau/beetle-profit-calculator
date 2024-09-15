package src.test.model.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.company.Customer;
import src.main.model.company.CustomersMap;
import src.main.model.exception.DuplicateException;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.*;

public class CustomersMapTest extends ModelTest {
    CustomersMap testCustomersMap;
    Customer testCustomer1Same;
    Customer testCustomer2;


    @BeforeEach
    void setUpCustomers() {
        testCustomersMap = new CustomersMap();
        testCustomer1Same = new Customer("Zenia", "_zenialau_", "Canada");
        testCustomer2 = new Customer("Katherine", "katherinelim", "Richmond");
    }

    @Test
    void testConstructor() {
        assertTrue(testCustomersMap.getCustomersMap().isEmpty());
        assertEquals(0, testCustomersMap.getTotalRevenue());
    }

    @Test
    void testAddBuyerOnce() {
        try {
            testCustomersMap.addBuyer(testCustomer1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        assertEquals(1, testCustomersMap.getListOfCustomers().size());
        assertEquals(0, testCustomersMap.getAmountPurchased(testCustomer1));
    }
    @Test
    void testAddBuyerMultiple() {
        try {
            testCustomersMap.addBuyer(testCustomer1);
            testCustomersMap.addBuyer(testCustomer2);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        assertEquals(2, testCustomersMap.getListOfCustomers().size());
        assertEquals(0, testCustomersMap.getAmountPurchased(testCustomer1));
        assertEquals(0, testCustomersMap.getAmountPurchased(testCustomer2));
    }
    @Test
    void testAddBuyerDuplicate() {
        try {
            testCustomersMap.addBuyer(testCustomer1);
            testCustomersMap.addBuyer(testCustomer1Same);
            fail("DuplicateException expected but not thrown.");
        } catch (DuplicateException e) {
            // expected
        }
        assertEquals(1, testCustomersMap.getListOfCustomers().size());
        assertTrue(testCustomersMap.getListOfCustomers().contains(testCustomer1));
        assertEquals(0, testCustomersMap.getAmountPurchased(testCustomer1));
    }

    @Test
    void testAddPurchaseOnce() {
        try {
            testCustomersMap.addBuyer(testCustomer1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testPurchase1.addItem(i1); // $50
        testCustomersMap.addPurchase(testCustomer1, testPurchase1);
        assertEquals(50, testCustomersMap.getAmountPurchased(testCustomer1));
        assertEquals(50, testCustomersMap.getTotalRevenue());
    }
    @Test
    void testAddPurchaseMultipleSameBuyer() {
        try {
            testCustomersMap.addBuyer(testCustomer1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testPurchase1.addItem(i1); // $50
        testPurchase2.addItem(i2); // 10
        testCustomersMap.addPurchase(testCustomer1, testPurchase1);
        testCustomersMap.addPurchase(testCustomer1, testPurchase2);
        assertEquals(60, testCustomersMap.getAmountPurchased(testCustomer1));
        assertEquals(60, testCustomersMap.getTotalRevenue());
    }
    @Test
    void testAddPurchaseMultipleDiffBuyer() {
        try {
            testCustomersMap.addBuyer(testCustomer1);
            testCustomersMap.addBuyer(testCustomer2);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testPurchase1.addItem(i1); // $50
        testPurchase2.addItem(i2); // 10
        testCustomersMap.addPurchase(testCustomer1, testPurchase1);
        testCustomersMap.addPurchase(testCustomer2, testPurchase2);
        assertEquals(50, testCustomersMap.getAmountPurchased(testCustomer1));
        assertEquals(10, testCustomersMap.getAmountPurchased(testCustomer2));
        assertEquals(60, testCustomersMap.getTotalRevenue());
    }

}
