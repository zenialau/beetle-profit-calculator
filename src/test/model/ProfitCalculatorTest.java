package src.test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.ProfitCalculator;
import src.main.model.company.BuyersMap;
import src.main.model.company.SuppliersMap;

import static org.junit.jupiter.api.Assertions.*;

public class ProfitCalculatorTest extends ModelTest{
    ProfitCalculator testCalculator;
    BuyersMap buyers;
    SuppliersMap suppliers;

    @BeforeEach
    void setUp() {
        testPurchase1.addItem(i1);
        testPurchase1.addItem(i2);

        buyers = new BuyersMap();
        try {
            buyers.addBuyer(testBuyer1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        buyers.addPurchase(testBuyer1, testPurchase1);

        suppliers = new SuppliersMap();
        try {
            suppliers.addSupplier(testSupplier1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        suppliers.addPurchase(testSupplier1, testPurchase1);

        testCalculator = new ProfitCalculator(buyers, suppliers);
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
        buyers.addPurchase(testBuyer1, testPurchase1);
        testCalculator.update();
        assertEquals(60, testCalculator.getProfit());
    }
}
