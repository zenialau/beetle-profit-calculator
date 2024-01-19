package src.test.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class AddPurchaseTest {

    @Test
    void parseStringInputToDouble() {
        Object input = "lskdfj";
        try {
            double d = (Double) input;
            fail("ClassCastException expected.");
        } catch (NumberFormatException e) {
            fail("ClassCastException expected.");
        } catch (ClassCastException e) {
            // expected
        }
    }

}
