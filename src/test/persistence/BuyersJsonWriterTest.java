package src.test.persistence;

import org.junit.jupiter.api.Test;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.persistence.BuyersJsonReader;
import src.main.persistence.JsonWriter;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BuyersJsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            BuyersMap bm = new BuyersMap();
            JsonWriter writer = new JsonWriter("./data/testJsonWriter/my\0illegal:fileNmae.json");
            writer.open();
            fail("IOException was expected.");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBuyersMap() {
        try {
            BuyersMap bm = new BuyersMap();
            JsonWriter writer = new JsonWriter("./data/testJsonWriter/testWriterEmptyBuyersMap.json");
            writer.open();
            writer.write(bm);
            writer.close();

            BuyersJsonReader reader = new BuyersJsonReader("./data/testJsonWriter/testWriterEmptyBuyersMap.json");
            bm = reader.read();
            assertTrue(bm.getListOfBuyers().isEmpty());
            assertEquals(0, bm.getTotalRevenue());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testBuyersWriterOne() {
        try {
            BuyersMap bm = new BuyersMap();
            Buyer buyer = new Buyer("Zen", "_zenialau_", "coquitlam");
            bm.addBuyer(buyer);

            Purchase purchaseDec4 = new Purchase();
            purchaseDec4.setPurchaseDate("2023-12-04");
            purchaseDec4.addItem(beetle);
            purchaseDec4.addItem(butterfly);
            bm.addPurchase(buyer, purchaseDec4);

            Purchase purchaseNov23 = new Purchase();
            purchaseNov23.setPurchaseDate("2023-11-23");
            purchaseNov23.addItem(bug);
            bm.addPurchase(buyer, purchaseNov23);

            JsonWriter writer = new JsonWriter("./data/testJsonWriter/testBuyersWriterOne.json");
            writer.open();
            writer.write(bm);
            writer.close();

            // same test as testBuyersReaderOne() in BuyersJsonReaderTest.java
            BuyersJsonReader reader = new BuyersJsonReader("./data/testJsonWriter/testBuyersWriterOne.json");
            BuyersMap bmRead = reader.read();
            assertEquals(181.5, bmRead.getTotalRevenue());
            Set<Buyer> buyers = bmRead.getListOfBuyers();
            assertEquals(1, buyers.size());
            assertTrue(buyers.contains(buyer));
            PurchaseList plRead = bmRead.getPurchaseList(buyer);
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
            BuyersMap bm = new BuyersMap();
            Buyer buyer = new Buyer("Zen", "_zenialau_", "coquitlam");
            bm.addBuyer(buyer);
            Buyer buyer2 = new Buyer("Jamie", "ig", "Vancouver");
            bm.addBuyer(buyer2);

            Purchase purchaseDec4 = new Purchase();
            purchaseDec4.setPurchaseDate("2023-12-04");
            purchaseDec4.addItem(beetle);
            purchaseDec4.addItem(butterfly);
            bm.addPurchase(buyer, purchaseDec4);

            Purchase purchaseNov23 = new Purchase();
            purchaseNov23.setPurchaseDate("2023-11-23");
            purchaseNov23.addItem(bug);
            bm.addPurchase(buyer, purchaseNov23);

            JsonWriter writer = new JsonWriter("./data/testJsonWriter/testBuyersWriterTwo.json");
            writer.open();
            writer.write(bm);
            writer.close();

            // same test as testBuyersReaderTwo() in BuyersJsonReaderTest.java
            BuyersJsonReader reader = new BuyersJsonReader("./data/testJsonWriter/testBuyersWriterTwo.json");
            BuyersMap bmRead = reader.read();
            assertEquals(181.5, bmRead.getTotalRevenue());
            Set<Buyer> buyers = bmRead.getListOfBuyers();
            assertEquals(2, buyers.size());

        } catch (DuplicateException e) {
            fail("DuplicateException thrown but not expected.");
        } catch (IOException e) {
            fail("Exception should nto have been thrown.");
        }
    }
}
