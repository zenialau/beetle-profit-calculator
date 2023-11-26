package src.test.model.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.company.Supplier;
import src.main.model.company.SuppliersMap;
import src.main.model.exception.DuplicateException;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuppliersMapTest extends ModelTest {
    SuppliersMap testSuppliersMap;
    Supplier testSupplier1Same;
    Supplier testSupplier2;

    @BeforeEach
    void setUpSuppliers() {
        testSuppliersMap = new SuppliersMap();
        testSupplier1Same = new Supplier("Nigel", "Canada");
        testSupplier2 = new Supplier("Katherine", "Canada");
    }

    @Test
    void testConstructor() {
        assertTrue(testSuppliersMap.getSuppliersMap().isEmpty());
        assertEquals(0, testSuppliersMap.getTotalExpense());
    }

    @Test
    void testAddSupplierOnce() {
        try {
            testSuppliersMap.addSupplier(testSupplier1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        assertEquals(1, testSuppliersMap.getListOfSuppliers().size());
        assertEquals(0, testSuppliersMap.getAmountPurchased(testSupplier1));
    }
    @Test
    void testAddSupplierMultiple() {
        try {
            testSuppliersMap.addSupplier(testSupplier1);
            testSuppliersMap.addSupplier(testSupplier2);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        assertEquals(2, testSuppliersMap.getListOfSuppliers().size());
        assertEquals(0, testSuppliersMap.getAmountPurchased(testSupplier1));
        assertEquals(0, testSuppliersMap.getAmountPurchased(testSupplier2));
    }
    @Test
    void testAddSupplierDuplicate() {
        try {
            testSuppliersMap.addSupplier(testSupplier1);
            testSuppliersMap.addSupplier(testSupplier1Same);
            fail("DuplicateException expected but not thrown.");
        } catch (DuplicateException e) {
            // expected
        }
        assertEquals(1, testSuppliersMap.getListOfSuppliers().size());
        assertTrue(testSuppliersMap.getListOfSuppliers().contains(testSupplier1));
        assertEquals(0, testSuppliersMap.getAmountPurchased(testSupplier1));
    }

    @Test
    void testAddPurchaseOnce() {
        try {
            testSuppliersMap.addSupplier(testSupplier1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testPurchase1.addItem(i1); // $50
        testSuppliersMap.addPurchase(testSupplier1, testPurchase1);
        assertEquals(50, testSuppliersMap.getAmountPurchased(testSupplier1));
        assertEquals(50, testSuppliersMap.getTotalExpense());
    }
    @Test
    void testAddPurchaseMultipleSameSupplier() {
        try {
            testSuppliersMap.addSupplier(testSupplier1);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testPurchase1.addItem(i1); // $50
        testPurchase2.addItem(i2); // 10
        testSuppliersMap.addPurchase(testSupplier1, testPurchase1);
        testSuppliersMap.addPurchase(testSupplier1, testPurchase2);
        assertEquals(60, testSuppliersMap.getAmountPurchased(testSupplier1));
        assertEquals(60, testSuppliersMap.getTotalExpense());
    }
    @Test
    void testAddPurchaseMultipleDiffSupplier() {
        try {
            testSuppliersMap.addSupplier(testSupplier1);
            testSuppliersMap.addSupplier(testSupplier2);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
        testPurchase1.addItem(i1); // $50
        testPurchase2.addItem(i2); // 10
        testSuppliersMap.addPurchase(testSupplier1, testPurchase1);
        testSuppliersMap.addPurchase(testSupplier2, testPurchase2);
        assertEquals(50, testSuppliersMap.getAmountPurchased(testSupplier1));
        assertEquals(10, testSuppliersMap.getAmountPurchased(testSupplier2));
        assertEquals(60, testSuppliersMap.getTotalExpense());
    }
}
