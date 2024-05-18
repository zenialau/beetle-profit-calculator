package src.main.ui.gui.traders.specific;

import src.main.model.company.Buyer;
import src.main.model.oberver.PurchaseObserver;
import src.main.ui.gui.GUISystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.main.ui.gui.PanelsContainer.BUYERS_PANEL;

public class SpecificBuyerPanel extends GenericSpecificPanel implements ActionListener, PurchaseObserver {

    private Buyer buyer;

    public SpecificBuyerPanel(GUISystem system, JPanel container, Buyer buyer) {
        super(system, container);
        this.buyer = buyer;
        this.purchaseList = system.getBuyersMap().getPurchaseList(buyer);

        addTitle();
        addSpace();
        addScrollPane();
        addPurchaseList();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: add "back" button and "add buyer" button
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
        titleLabel = new JLabel("   " + buyer.getName() + " [ " + buyer.getIgAccount() + " ] : " + buyer.getAddress());
        setupTitle(titleLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == backButton) {
            cardLayout.show(container, BUYERS_PANEL);
        }
        else if (e.getSource() == addPurchaseButton) {
//            new AddPurchaseWindow(system, buyer); // trader
            new AddPurchaseWindowGeneral(system, buyer, "b");
        }
    }

    @Override
    public void update() {
        purchasesContainer.remove(purchasesTable);
        addPurchaseList();
        purchasesContainer.setVisible(false); // such that the display updates right away
        purchasesContainer.setVisible(true);
    }

}
