package src.test.model.inventory;

import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerTest extends ModelTest {

    @Test
    void testConstructor() {
        assertEquals("frame", testContainer.getType());
        assertEquals("small", testContainer.getSize());
        assertEquals(10, testContainer.getCost());
    }
}
