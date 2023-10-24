package src.test.model.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.inventory.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testContainer {
    Container testContainer;

    @BeforeEach
    void runBefore() {
        testContainer = new Container("frame", "small", 10);
    }

    @Test
    void testConstructor() {
        assertEquals("frame", testContainer.getType());
        assertEquals("small", testContainer.getSize());
        assertEquals(10, testContainer.getCost());
    }
}
