package src.test.model.company;

import org.junit.jupiter.api.BeforeEach;
import src.main.model.company.BuyersMap;

public class BuyersMapTest extends TradersMapTest {
    BuyersMap testBuyersMap;

    @BeforeEach
    void setUpBuyers() {
        testBuyersMap = new BuyersMap();
        testMap = testBuyersMap;
    }
}
