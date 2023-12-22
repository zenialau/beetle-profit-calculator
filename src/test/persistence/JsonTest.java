package src.test.persistence;

import org.junit.jupiter.api.BeforeEach;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Reference: JsonSerializationDemo
public class JsonTest {
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
