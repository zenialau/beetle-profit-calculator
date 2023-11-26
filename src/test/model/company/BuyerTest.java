package src.test.model.company;

import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyerTest extends ModelTest {

    @Test
    void testConstructor() {
        assertEquals("Zenia", testBuyer1.getName());
        assertEquals("_zenialau_", testBuyer1.getIgAccount());
        assertEquals("Coquitlam", testBuyer1.getAddress());
    }

}
