package src.test.model.company;

import org.junit.jupiter.api.BeforeEach;
import src.main.model.company.SuppliersMap;

public class SuppliersMapTest extends TradersMapTest{
    SuppliersMap testSuppliersMap;

    @BeforeEach
    void setUpSuppliers() {
        testSuppliersMap = new SuppliersMap();
        testMap = testSuppliersMap;
    }
}
