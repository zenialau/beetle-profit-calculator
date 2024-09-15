package src.test.persistence;

import org.junit.jupiter.api.Test;
import src.main.model.company.Customer;
import src.main.model.company.CustomersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.persistence.CustomersJsonReader;
import src.main.persistence.JsonWriter;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CustomersJsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CustomersMap bm = new CustomersMap();
            JsonWriter writer = new JsonWriter("./data/testJsonWriter/my\0illegal:fileNmae.json");
            writer.open();
            fail("IOException was expected.");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCustomersMap() {
        try {
            CustomersMap bm = new CustomersMap();
            JsonWriter writer = new JsonWriter("./data/testJsonWriter/testWriterEmptyBuyersMap.json");
            writer.open();
            writer.write(bm);
            writer.close();

            CustomersJsonReader reader = new CustomersJsonReader("./data/testJsonWriter/testWriterEmptyBuyersMap.json");
            bm = reader.read();
            assertTrue(bm.getListOfCustomers().isEmpty());
            assertEquals(0, bm.getTotalRevenue());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testBuyersWriterOne() {
        try {
            CustomersMap bm = new CustomersMap();
            Customer customer = new Customer("Zen", "_zenialau_", "coquitlam");
            bm.addBuyer(customer);

            Purchase purchaseDec4 = new Purchase();
            purchaseDec4.setPurchaseDate("2023-12-04");
            purchaseDec4.addItem(beetle);
            purchaseDec4.addItem(butterfly);
            bm.addPurchase(customer, purchaseDec4);

            Purchase purchaseNov23 = new Purchase();
            purchaseNov23.setPurchaseDate("2023-11-23");
            purchaseNov23.addItem(bug);
            bm.addPurchase(customer, purchaseNov23);

            JsonWriter writer = new JsonWriter("./data/testJsonWriter/testBuyersWriterOne.json");
            writer.open();
            writer.write(bm);
            writer.close();

            // same test as testBuyersReaderOne() in BuyersJsonReaderTest.java
            CustomersJsonReader reader = new CustomersJsonReader("./data/testJsonWriter/testBuyersWriterOne.json");
            CustomersMap bmRead = reader.read();
            assertEquals(181.5, bmRead.getTotalRevenue());
            Set<Customer> customers = bmRead.getListOfCustomers();
            assertEquals(1, customers.size());
            assertTrue(customers.contains(customer));
            PurchaseList plRead = bmRead.getPurchaseList(customer);
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
    void testBuyersWriterTwo() {
        try {
            CustomersMap bm = new CustomersMap();
            Customer customer = new Customer("Zen", "_zenialau_", "coquitlam");
            bm.addBuyer(customer);
            Customer customer2 = new Customer("Jamie", "ig", "Vancouver");
            bm.addBuyer(customer2);

            Purchase purchaseDec4 = new Purchase();
            purchaseDec4.setPurchaseDate("2023-12-04");
            purchaseDec4.addItem(beetle);
            purchaseDec4.addItem(butterfly);
            bm.addPurchase(customer, purchaseDec4);

            Purchase purchaseNov23 = new Purchase();
            purchaseNov23.setPurchaseDate("2023-11-23");
            purchaseNov23.addItem(bug);
            bm.addPurchase(customer, purchaseNov23);

            JsonWriter writer = new JsonWriter("./data/testJsonWriter/testBuyersWriterTwo.json");
            writer.open();
            writer.write(bm);
            writer.close();

            // same test as testBuyersReaderTwo() in BuyersJsonReaderTest.java
            CustomersJsonReader reader = new CustomersJsonReader("./data/testJsonWriter/testBuyersWriterTwo.json");
            CustomersMap bmRead = reader.read();
            assertEquals(181.5, bmRead.getTotalRevenue());
            Set<Customer> customers = bmRead.getListOfCustomers();
            assertEquals(2, customers.size());

        } catch (DuplicateException e) {
            fail("DuplicateException thrown but not expected.");
        } catch (IOException e) {
            fail("Exception should nto have been thrown.");
        }
    }
}
