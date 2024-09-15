package src.test.persistence;

import org.junit.jupiter.api.Test;
import src.main.model.company.Customer;
import src.main.model.company.CustomersMap;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.persistence.CustomersJsonReader;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Reference: JsonSerializationDemo
public class CustomersJsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        CustomersJsonReader reader = new CustomersJsonReader("./data/noSuchFile.json");
        try {
            CustomersMap bm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testCustomersReaderEmpty() {
        CustomersJsonReader reader = new CustomersJsonReader("data/testJsonReader/testBuyersReaderEmpty.json");
        try {
            CustomersMap bm = reader.read();
            assertTrue(bm.getListOfCustomers().isEmpty());
            assertEquals(0, bm.getTotalRevenue());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testBuyersReaderOne() {
        CustomersJsonReader reader = new CustomersJsonReader("data/testJsonReader/testBuyersReaderOne.json");
        try {
            CustomersMap bm = reader.read();
            assertEquals(181.5, bm.getTotalRevenue());
            Set<Customer> customers = bm.getListOfCustomers();
            assertEquals(1, customers.size());

            Customer customer = new Customer("Zen", "_zenialau_", "coquitlam");
            assertTrue(customers.contains(customer));

            PurchaseList pl = bm.getPurchaseList(customer);
            Purchase purchaseDec4 = pl.get(0);
            Purchase purchaseNov23 = pl.get(1);

            checkPurchase("2023-12-04", itemsDec4, purchaseDec4);
            checkPurchase("2023-11-23", itemsNov23, purchaseNov23);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testBuyersReaderTwo() {
        CustomersJsonReader reader = new CustomersJsonReader("data/testJsonReader/testBuyersReaderTwo.json");
        try {
            CustomersMap bm = reader.read();
            assertEquals(181.5, bm.getTotalRevenue());
            Set<Customer> customers = bm.getListOfCustomers();
            assertEquals(2, customers.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
