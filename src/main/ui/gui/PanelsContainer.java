package src.main.ui.gui;

import javax.swing.*;
import java.awt.*;

// represents the container that stores all the panels for CardLayout
public class PanelsContainer {

    private GUISystem system;

    private JPanel container;
    private CardLayout cardLayout;

    private MainPanel mainPanel;
    private BuyersPanel buyersPanel;

    // EFFECTS: set up container and panels
    public PanelsContainer(GUISystem system) {
        this.system = system;
        container = new JPanel();
        cardLayout = new CardLayout();
        container.setLayout(cardLayout);
        setupPanels();
    }

    // MODIFIES: this
    // EFFECTS: set up sub-panels in container
    private void setupPanels() {
        setupMainPanel();
        setupBuyersPanel();
        //...
        container.add(mainPanel);
        container.add(buyersPanel);
        cardLayout.addLayoutComponent(mainPanel, "mainPanel");
        cardLayout.addLayoutComponent(buyersPanel, "buyersPanel");
    }

    // MODIFIES: this
    // EFFECTS: initialize and set up mainPanel
    private void setupMainPanel() {
        mainPanel = new MainPanel(container);
    }

    // MODIFIES: this
    // EFFECTS: initialize and set up buyersPanel
    private void setupBuyersPanel() {
        buyersPanel = new BuyersPanel(this.system, container);
    }

    public JPanel getContainer() {
        return container;
    }

}
