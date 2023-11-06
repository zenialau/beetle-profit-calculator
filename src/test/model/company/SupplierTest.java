package src.test.model.company;

import org.junit.jupiter.api.Test;
import src.test.model.ModelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupplierTest extends ModelTest {

    @Test
    void testConstructor() {
        assertEquals("Nigel", testSupplier.getName());
        assertEquals("Canada", testSupplier.getCountry());
    }

}
