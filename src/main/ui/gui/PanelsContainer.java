package src.main.ui.gui;

import src.main.ui.gui.traders.BuyersPanel;
import src.main.ui.gui.traders.SuppliersPanel;

import javax.swing.*;
import java.awt.*;

// represents the container that stores all the panels for CardLayout
public class PanelsContainer {

    public final static String MAIN_PANEL = "mainPanel";
    public final static String BUYERS_PANEL = "buyersPanel";
    public final static String SUPPLIERS_PANEL = "suppliersPanel";

    private GUISystem system;

    private JPanel container;
    private CardLayout cardLayout;

    private MainPanel mainPanel;
    private BuyersPanel buyersPanel;
    private SuppliersPanel suppliersPanel;

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
        container.add(mainPanel);
        cardLayout.addLayoutComponent(mainPanel, MAIN_PANEL);

        setupBuyersPanel();
        container.add(buyersPanel);
        cardLayout.addLayoutComponent(buyersPanel, BUYERS_PANEL);

        setupSuppliersPanel();
        container.add(suppliersPanel);
        cardLayout.addLayoutComponent(suppliersPanel, SUPPLIERS_PANEL);

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
        system.getBuyersMap().addObserver(buyersPanel);
    }

    // MODIFIES: this
    // EFFECTS: initialize and set up suppliersPanel
    private void setupSuppliersPanel() {
        suppliersPanel = new SuppliersPanel(this.system, container);
        system.getSuppliersMap().addObserver(suppliersPanel);
    }

    public JPanel getContainer() {
        return container;
    }

}
