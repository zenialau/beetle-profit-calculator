package src.test.persistence;

import org.junit.jupiter.api.Test;
import src.main.model.company.Supplier;
import src.main.model.company.SuppliersMap;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.persistence.SuppliersJsonReader;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuppliersJsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        SuppliersJsonReader reader = new SuppliersJsonReader("./data/noSuchFile.json");
        try {
            SuppliersMap sm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testSuppliersReaderEmpty() {
        SuppliersJsonReader reader = new SuppliersJsonReader("data/testJsonReader/testSuppliersReaderEmpty.json");
        try {
            SuppliersMap sm = reader.read();
            assertTrue(sm.getListOfSuppliers().isEmpty());
            assertEquals(0, sm.getTotalExpense());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testSuppliersReaderOne() {
        SuppliersJsonReader reader = new SuppliersJsonReader("data/testJsonReader/testSuppliersReaderOne.json");
        try {
            SuppliersMap sm = reader.read();
            assertEquals(181.5, sm.getTotalExpense());
            Set<Supplier> suppliers = sm.getListOfSuppliers();
            assertEquals(1, suppliers.size());

            Supplier supplier = new Supplier("Zen", "Canada");
            assertTrue(suppliers.contains(supplier));

            PurchaseList pl = sm.getPurchaseList(supplier);
            Purchase purchaseDec4 = pl.get(0);
            Purchase purchaseNov23 = pl.get(1);

            checkPurchase("2023-12-04", itemsDec4, purchaseDec4);
            checkPurchase("2023-11-23", itemsNov23, purchaseNov23);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testSuppliersReaderTwo() {
        SuppliersJsonReader reader = new SuppliersJsonReader("data/testJsonReader/testSuppliersReaderTwo.json");
        try {
            SuppliersMap sm = reader.read();
            assertEquals(181.5, sm.getTotalExpense());
            Set<Supplier> suppliers = sm.getListOfSuppliers();
            assertEquals(2, suppliers.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
