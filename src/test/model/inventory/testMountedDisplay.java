package src.test.model.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.model.inventory.Beetle;
import src.main.model.inventory.Container;
import src.main.model.inventory.MountedDisplay;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testMountedDisplay {
    MountedDisplay testDisplay;
    Beetle testBeetle;
    Container testContainer;

    @BeforeEach
    void runBefore() {
        testBeetle = new Beetle("Megasoma gyas", 100, 95);
        testContainer = new Container("frame", "small", 10);
        testDisplay = new MountedDisplay(testBeetle, testContainer, 60, 200);
    }

    @Test
    void testConstructor() {
        assertEquals(testBeetle, testDisplay.getBeetle());
        assertEquals(testContainer, testDisplay.getContainer());
        assertEquals(60, testDisplay.getTimeSpent());
        assertEquals(200, testDisplay.getSellingPrice());
    }
}
