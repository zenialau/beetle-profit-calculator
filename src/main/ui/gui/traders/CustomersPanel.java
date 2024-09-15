package src.main.ui.gui.traders;

import src.main.model.company.Customer;
import src.main.model.company.CustomersMap;
import src.main.model.inventory.Purchase;
import src.main.model.oberver.CustomerObserver;
import src.main.ui.gui.traders.window.AddBuyerWindow;
import src.main.ui.gui.GUISystem;
import src.main.ui.gui.traders.specific.SpecificBuyerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.main.ui.gui.PanelsContainer.MAIN_PANEL;

// customers screen that shows existing customers and allow the option to add buyer or purchase,
// or go back to main menu
public class CustomersPanel extends TradersPanel implements CustomerObserver {

    // EFFECTS: constructs the customers panel
    public CustomersPanel(GUISystem system, JPanel container) {
        super(system, container);
        addTitle("Customers");
        addSpace();
        addScrollPane();
        addCustomers();
        addButtons("Customer");
    }

    // MODIFIES: this
    // EFFECTS: add customers as buttons to tradersContainer
    private void addCustomers() {
        CustomersMap customers = system.getCustomersMap();
        tradersContainerHeight = 0;

        for (Customer b : customers) {
            Purchase lastPurchase = customers.getLastPurchase(b);
            String lastPurchasePrint = "";
            if (lastPurchase != null) {
                lastPurchasePrint = lastPurchase.toString();
            }
            addBuyerButton(b, lastPurchasePrint);
        }
    }

    // MODIFIES: this
    // EFFECTS: make a button for the specific buyer and add it to tradersContainer
    private void addBuyerButton(Customer b, String lastPurchasePrint) {
        JButton buyerButton = new JButton(b.getName() + " ( " + b.getIgAccount() + " ) "
                + "; Last purchase: " + lastPurchasePrint);
        setButtonDimensions(buyerButton);

        makeBuyerPanel(b);
        setActionListener(b, buyerButton);

        tradersContainer.add(buyerButton);
        tradersContainerHeight += CUSTOMERS_BUTTON_HEIGHT;

        tradersContainer.setPreferredSize(new Dimension(CONTAINER_WIDTH, tradersContainerHeight));
    }

    // MODIFIES: this
    // EFFECTS: create panel for specific buyer
    private void makeBuyerPanel(Customer customer) {
        SpecificBuyerPanel specPanel = new SpecificBuyerPanel(system, container, customer);
        system.getCustomersMap().addObserver(specPanel);
        container.add(specPanel);
        cardLayout.addLayoutComponent(specPanel, customer.getIgAccount());
    }

    // MODIFIES: this
    // EFFECTS: create specific ActionListener for specific button
    private void setActionListener(Customer customer, JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container, customer.getIgAccount()); // igAccount as constraint
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(container, MAIN_PANEL);
        } else if (e.getSource() == addTraderButton) {
            new AddBuyerWindow(system);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates this panel whenever a buyer is added
    @Override
    public void update(Customer customer) {
        addBuyerButton(customer, "");
    }

}
