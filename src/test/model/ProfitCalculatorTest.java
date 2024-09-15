package src.test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.ProfitCalculator;
import src.main.model.company.CustomersMap;
import src.main.model.company.SuppliersMap;

import static org.junit.jupiter.api.Assertions.*;

public class ProfitCalculatorTest extends ModelTest{
    ProfitCalculator testCalculator;
    CustomersMap customers;
    SuppliersMap suppliers;

    @BeforeEach
    void setUp() {
        testPurchase1.addItem(i1);
        testPurchase1.addItem(i2);

        customers = new CustomersMap();
        try {
            customers.addBuyer(testCustomer1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        customers.addPurchase(testCustomer1, testPurchase1);

        suppliers = new SuppliersMap();
        try {
            suppliers.addSupplier(testSupplier1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        suppliers.addPurchase(testSupplier1, testPurchase1);

        testCalculator = new ProfitCalculator(customers, suppliers);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCalculator.getRevenue());
        assertEquals(0, testCalculator.getExpense());
    }

    @Test
    void testGetProfit0() {
        testCalculator.update();
        assertEquals(0, testCalculator.getProfit());
    }
    @Test
    void testGetProfitGeneral() {
        customers.addPurchase(testCustomer1, testPurchase1);
        testCalculator.update();
        assertEquals(60, testCalculator.getProfit());
    }
}
