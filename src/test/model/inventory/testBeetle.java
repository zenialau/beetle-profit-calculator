package src.test.model.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.inventory.Beetle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testBeetle {
    Beetle testBeetle;

    @BeforeEach
    void runBefore() {
        testBeetle = new Beetle("Megasoma gyas", 100, 95);
    }

    @Test
    void testConstructor() {
        assertEquals("Megasoma gyas", testBeetle.getLatinName());
        assertEquals(100, testBeetle.getCost());
        assertEquals(95, testBeetle.getSize());
    }
}
