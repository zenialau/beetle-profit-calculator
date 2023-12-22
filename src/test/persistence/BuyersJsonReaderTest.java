package src.test.persistence;

import org.junit.jupiter.api.Test;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.persistence.BuyersJsonReader;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Reference: JsonSerializationDemo
public class BuyersJsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        BuyersJsonReader reader = new BuyersJsonReader("./data/noSuchFile.json");
        try {
            BuyersMap bm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testBuyersReaderEmpty() {
        BuyersJsonReader reader = new BuyersJsonReader("data/testJsonReader/testBuyersReaderEmpty.json");
        try {
            BuyersMap bm = reader.read();
            assertTrue(bm.getListOfBuyers().isEmpty());
            assertEquals(0, bm.getTotalRevenue());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testBuyersReaderOne() {
        BuyersJsonReader reader = new BuyersJsonReader("data/testJsonReader/testBuyersReaderOne.json");
        try {
            BuyersMap bm = reader.read();
            assertEquals(181.5, bm.getTotalRevenue());
            Set<Buyer> buyers = bm.getListOfBuyers();
            assertEquals(1, buyers.size());

            Buyer buyer = new Buyer("Zen", "_zenialau_", "coquitlam");
            assertTrue(buyers.contains(buyer));

            PurchaseList pl = bm.getPurchaseList(buyer);
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
        BuyersJsonReader reader = new BuyersJsonReader("data/testJsonReader/testBuyersReaderTwo.json");
        try {
            BuyersMap bm = reader.read();
            assertEquals(181.5, bm.getTotalRevenue());
            Set<Buyer> buyers = bm.getListOfBuyers();
            assertEquals(2, buyers.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
