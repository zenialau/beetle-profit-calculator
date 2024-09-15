package src.test.model.company;

import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest extends ModelTest {

    @Test
    void testConstructor() {
        assertEquals("Zenia", testCustomer1.getName());
        assertEquals("_zenialau_", testCustomer1.getIgAccount());
        assertEquals("Coquitlam", testCustomer1.getAddress());
    }

}
