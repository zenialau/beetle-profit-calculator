package src.main.ui.gui.traders;

import src.main.model.company.Supplier;
import src.main.model.company.SuppliersMap;
import src.main.ui.gui.GUISystem;
import src.main.ui.gui.traders.window.AddSupplierWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.main.ui.gui.PanelsContainer.MAIN_PANEL;

// suppliers screen that shows existing suppliers and allow the option to add supplier or purchase,
// or go back to main menu
public class SuppliersPanel extends TradersPanel { //implements SupplierObserver

    // EFFECTS: constructs the suppliers panel
    public SuppliersPanel(GUISystem system, JPanel container) {
        super(system, container);
        addTitle("Suppliers");
        addSpace();
        addScrollPane();
        addSuppliers();
        addButtons("Supplier");
    }

    // MODIFIES: this
    // EFFECTS: add suppliers as buttons to tradersContainer
    private void addSuppliers() {
        SuppliersMap suppliers = system.getSuppliersMap();
        tradersContainerHeight = 0;

        for (Supplier s : suppliers) {
            addSupplierButton(s);
        }
    }

    // MODIFIES: this
    // EFFECTS: make a button for the specific supplier and ad ti to tradersContainer
    private void addSupplierButton(Supplier s) {
        JButton supplierButton = new JButton(s.getName() + " [ " + s.getCountry() + " ] ");
        setButtonDimensions(supplierButton);
        makeSupplierPanel(s);
        setActionListener(s, supplierButton);
        tradersContainer.add(supplierButton);
        tradersContainerHeight += BUYERS_BUTTON_HEIGHT;
        tradersContainer.setPreferredSize(new Dimension(CONTAINER_WIDTH, tradersContainerHeight));
    }

    // MODIFIES: this
    // EFFECTS: create panel for specific supplier
    private void makeSupplierPanel(Supplier supplier) {
        SpecificSupplierPanel specPanel = new SpecificSupplierPanel(system, container, supplier);
        //!!!
    }

    // MODIFIES: this
    // EFFECTS: create specific ActionListener for specific button
    private void setActionListener(Supplier supplier, JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container, supplier.getName()); // name as constraint
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(container, MAIN_PANEL);
        } else if (e.getSource() == addTraderButton) {
            new AddSupplierWindow(system);
        }
    }

//    @Override
//    public void update(Supplier supplier) {
//        addSupplierButton();
//    }
}
