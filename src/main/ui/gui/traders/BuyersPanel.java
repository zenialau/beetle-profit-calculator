package src.main.ui.gui.traders;

import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.inventory.Purchase;
import src.main.ui.gui.AddBuyerWindow;
import src.main.ui.gui.GUISystem;
import src.main.ui.gui.SpecificBuyerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.main.ui.gui.PanelsContainer.MAIN_PANEL;

// buyers screen that shows existing buyers and allow the option to add buyer or purchase, or go back to main menu
public class BuyersPanel extends TradersPanel {

    // EFFECTS: constructs the buyers panel
    public BuyersPanel(GUISystem system, JPanel container) {
        super(system, container);

        addTitle("Customers");
        addSpace();
        addScrollPane();
        addBuyers();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: add buyers as buttons to buyersContainer
    private void addBuyers() {
        BuyersMap buyers = system.getBuyersMap();
        buyersContainerHeight = 0;

        for (Buyer b : buyers) {
            Purchase lastPurchase = buyers.getLastPurchase(b);
            String lastPurchasePrint = "";
            if (lastPurchase != null) {
                lastPurchasePrint = lastPurchase.toString();
            }
            addBuyerButton(b, lastPurchasePrint);
        }
    }

    // MODIFIES: this
    // EFFECTS: make a button for the specific buyer and add it to buyersContainer
    private void addBuyerButton(Buyer b, String lastPurchasePrint) {
        JButton buyerButton = new JButton(b.getName() + " ( " + b.getIgAccount() + " ) "
                + "; Last purchase: " + lastPurchasePrint);
        buyerButton.setMaximumSize(new Dimension(700, BUYERS_BUTTON_HEIGHT));

        makeBuyerPanel(b);
        setActionListener(b, buyerButton);

        buyersContainer.add(buyerButton);
        buyersContainerHeight += BUYERS_BUTTON_HEIGHT;

        buyersContainer.setPreferredSize(new Dimension(650, buyersContainerHeight));
    }

    // MODIFIES: this
    // EFFECTS: create panel for specific buyer
    private void makeBuyerPanel(Buyer buyer) {
        SpecificBuyerPanel specPanel = new SpecificBuyerPanel(system, container, buyer);
        system.getBuyersMap().addObserver(specPanel);
        container.add(specPanel);
        cardLayout.addLayoutComponent(specPanel, buyer.getIgAccount());
    }

    // MODIFIES: this
    // EFFECTS: create specific ActionListener for specific button
    private void setActionListener(Buyer buyer, JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container, buyer.getIgAccount()); // igAccount as constraint
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(container, MAIN_PANEL);
        } else if (e.getSource() == addBuyerButton) {
            new AddBuyerWindow(system);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates this panel whenever a buyer is added
    @Override
    public void update(Buyer buyer) {
        addBuyerButton(buyer, "");
    }

}
