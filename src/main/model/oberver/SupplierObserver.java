package src.main.model.oberver;

import src.main.model.company.Supplier;

public interface SupplierObserver {

    void update(Supplier supplier);
}
