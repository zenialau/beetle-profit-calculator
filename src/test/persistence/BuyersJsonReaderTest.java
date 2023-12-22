package src.test.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.persistence.BuyersJsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Reference: JsonSerializationDemo
public class BuyersJsonReaderTest extends JsonReaderTest {
    InventoryItem beetle;
    InventoryItem butterfly;
    InventoryItem bug;
    List<InventoryItem> itemsDec4;
    List<InventoryItem> itemsNov23;

    @BeforeEach
    void runBefore() {
        itemsDec4 = new ArrayList<>();
        itemsNov23 = new ArrayList<>();
        beetle = new InventoryItem("beetle", "mounted",
                87, "A-", "", 60);
        butterfly = new InventoryItem("butterfly", "customized",
                62.5, "A1", "blue", 95);
        bug = new InventoryItem("bug", "framed",
                30, "B", "first purchase discount", 26.5);
        itemsDec4.add(beetle);
        itemsDec4.add(butterfly);
        itemsNov23.add(bug);
    }

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
    void testReaderEmptyBuyers() {
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
    void testReaderGeneralBookCollectionOne() {
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
    void testReaderGeneralBookCollectionTwo() {
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
