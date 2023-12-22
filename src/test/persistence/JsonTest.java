package src.test.persistence;

import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Reference: JsonSerializationDemo
public class JsonTest {

    // EFFECTS: check that purchase is the same as expected
    protected void checkPurchase(String date, List<InventoryItem> items, Purchase purchase) {
        assertEquals(date, purchase.getPurchaseDate().toString());

        List<InventoryItem> list = purchase.getItems();
        assertEquals(items.size(), list.size());

        for (int i = 0; i < items.size(); i++) {
            assertEquals(items.get(i), list.get(i));
        }
    }
}
