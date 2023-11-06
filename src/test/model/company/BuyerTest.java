package src.test.model.company;

import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyerTest extends ModelTest {

    @Test
    void testConstructor() {
        assertEquals("Zenia", testBuyer.getName());
        assertEquals("_zenialau_", testBuyer.getIgAccount());
        assertEquals("Coquitlam", testBuyer.getAddress());
    }

}
