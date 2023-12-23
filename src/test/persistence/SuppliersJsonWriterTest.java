package src.test.persistence;

import org.junit.jupiter.api.Test;
import src.main.model.company.BuyersMap;
import src.main.model.company.Supplier;
import src.main.model.company.SuppliersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.persistence.JsonWriter;
import src.main.persistence.SuppliersJsonReader;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SuppliersJsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            BuyersMap bm = new BuyersMap();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileNmae.json");
            writer.open();
            fail("IOException was expected.");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySuppliersMap() {
        try {
            SuppliersMap sm = new SuppliersMap();
            JsonWriter writer = new JsonWriter("./data/testJsonWriter/testWriterEmptySuppliersMap.json");
            writer.open();
            writer.write(sm);
            writer.close();

            SuppliersJsonReader reader = new SuppliersJsonReader("./data/testJsonWriter/testWriterEmptySuppliersMap.json");
            sm = reader.read();
            assertTrue(sm.getListOfSuppliers().isEmpty());
            assertEquals(0, sm.getTotalExpense());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testSuppliersWriterOne() {
        try {
            SuppliersMap sm = new SuppliersMap();
            Supplier supplier = new Supplier("Zen", "Canada");
            sm.addSupplier(supplier);

            Purchase purchaseDec4 = new Purchase();
            purchaseDec4.setPurchaseDate("2023-12-04");
            purchaseDec4.addItem(beetle);
            purchaseDec4.addItem(butterfly);
            sm.addPurchase(supplier, purchaseDec4);

            Purchase purchaseNov23 = new Purchase();
            purchaseNov23.setPurchaseDate("2023-11-23");
            purchaseNov23.addItem(bug);
            sm.addPurchase(supplier, purchaseNov23);

            JsonWriter writer = new JsonWriter("./data/testJsonWriter/testSuppliersWriterOne.json");
            writer.open();
            writer.write(sm);
            writer.close();

            // same test as testSuppliersReaderOne() in SuppliersJsonReaderTest.java
            SuppliersJsonReader reader = new SuppliersJsonReader("./data/testJsonWriter/testSuppliersWriterOne.json");
            SuppliersMap smRead = reader.read();
            assertEquals(181.5, smRead.getTotalExpense());
            Set<Supplier> suppliers = smRead.getListOfSuppliers();
            assertEquals(1, suppliers.size());
            assertTrue(suppliers.contains(supplier));
            PurchaseList plRead = smRead.getPurchaseList(supplier);
            Purchase purchaseDec4Read = plRead.get(0);
            Purchase purchaseNov23Read = plRead.get(1);
            checkPurchase("2023-12-04", itemsDec4, purchaseDec4Read);
            checkPurchase("2023-11-23", itemsNov23, purchaseNov23Read);

        } catch (DuplicateException e) {
            fail("DuplicateException thrown but not expected.");
        } catch (IOException e) {
            fail("Exception should nto have been thrown.");
        }
    }

    @Test
    void testSuppliersWriterTwo() {
        try {
            SuppliersMap sm = new SuppliersMap();
            Supplier supplier = new Supplier("Zen", "Canada");
            sm.addSupplier(supplier);
            Supplier supplier2 = new Supplier("Jamie", "Hong Kong");
            sm.addSupplier(supplier2);

            Purchase purchaseDec4 = new Purchase();
            purchaseDec4.setPurchaseDate("2023-12-04");
            purchaseDec4.addItem(beetle);
            purchaseDec4.addItem(butterfly);
            sm.addPurchase(supplier, purchaseDec4);

            Purchase purchaseNov23 = new Purchase();
            purchaseNov23.setPurchaseDate("2023-11-23");
            purchaseNov23.addItem(bug);
            sm.addPurchase(supplier, purchaseNov23);

            JsonWriter writer = new JsonWriter("./data/testJsonWriter/testSuppliersWriterTwo.json");
            writer.open();
            writer.write(sm);
            writer.close();

            // same test as testSuppliersReaderTwo() in SuppliersJsonReaderTest.java
            SuppliersJsonReader reader = new SuppliersJsonReader("./data/testJsonWriter/testSuppliersWriterTwo.json");
            SuppliersMap smRead = reader.read();
            assertEquals(181.5, smRead.getTotalExpense());
            Set<Supplier> suppliers = smRead.getListOfSuppliers();
            assertEquals(2, suppliers.size());

        } catch (DuplicateException e) {
            fail("DuplicateException thrown but not expected.");
        } catch (IOException e) {
            fail("Exception should nto have been thrown.");
        }
    }

}
