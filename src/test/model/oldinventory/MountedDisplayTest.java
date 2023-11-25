package src.test.model.oldinventory;

import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MountedDisplayTest extends ModelTest {

    @Test
    void testConstructor() {
        assertEquals(testBeetle, testDisplay.getBeetle());
        assertEquals(testContainer, testDisplay.getContainer());
        assertEquals(60, testDisplay.getTimeSpent());
        assertEquals(150, testDisplay.getSellingPrice());
    }

    @Test
    void testSetPrice() {
        testDisplay.setSellingPrice(150);
        assertEquals(150, testDisplay.getSellingPrice());
    }
}
