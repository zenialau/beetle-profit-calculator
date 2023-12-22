package src.test.persistence;

import org.junit.jupiter.api.BeforeEach;
import src.main.model.inventory.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class JsonReaderTest extends JsonTest{
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
}
