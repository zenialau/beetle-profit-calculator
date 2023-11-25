package src.test.model.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.company.BuyersMap;
import src.test.model.ModelTest;

public class BuyersMapTest extends ModelTest {
    BuyersMap testBuyersMap;

    @BeforeEach
    void setUpBuyers() {
        testBuyersMap = new BuyersMap();
        testPurchase.addItem(testBeetle);
        testPurchase.addItem(testDisplay);
    }

    @Test
    void testConstructor() {

    }




}
