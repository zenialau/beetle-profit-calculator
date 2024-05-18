package src.main.ui.gui.traders.specific;

import src.main.model.company.Supplier;
import src.main.model.oberver.PurchaseObserver;
import src.main.ui.gui.GUISystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.main.ui.gui.PanelsContainer.SUPPLIERS_PANEL;

public class SpecificSupplierPanel extends GenericSpecificPanel implements ActionListener, PurchaseObserver {

    private Supplier supplier;

    public SpecificSupplierPanel(GUISystem system, JPanel container, Supplier supplier) {
        super(system, container);
        this.supplier = supplier;
        this.purchaseList = system.getSuppliersMap().getPurchaseList(supplier);

        addTitle();
        addSpace();
        addScrollPane();
        addPurchaseList();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: add "back" button and "add supplier" button
    private void addButtons() {
        setupButtonPanel();
        backButton = new JButton("< back");
        backButton.addActionListener(this);
        setupAddPurchaseButton();
        addPurchaseButton.addActionListener(this);
        finishButtonPanel();
    }

    // MODIFIES: this
    // EFFECTS: add title label to this panel
    private void addTitle() {
        titleLabel = new JLabel("   " + supplier.getName() + " [ " + supplier.getCountry() + " ]");
        setupTitle(titleLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(container, SUPPLIERS_PANEL);
        }
        else if (e.getSource() == addPurchaseButton) {
            new AddPurchaseWindowGeneral(system, supplier, "s");
        }
    }

    @Override
    public void update() {
        purchasesContainer.remove(purchasesTable);
        addPurchaseList();
        purchasesContainer.setVisible(false);
        purchasesContainer.setVisible(true);
    }

}
