package src.main.ui.gui;

import src.main.ui.gui.traders.CustomersPanel;
import src.main.ui.gui.traders.SuppliersPanel;

import javax.swing.*;
import java.awt.*;

// represents the container that stores all the panels for CardLayout
public class PanelsContainer {

    public final static String MAIN_PANEL = "mainPanel";
    public final static String CUSTOMERS_PANEL = "customersPanel";
    public final static String SUPPLIERS_PANEL = "suppliersPanel";
    public final static String CALCULATOR_PANEL = "calculatorPanel";

    private GUISystem system;

    private JPanel container;
    private CardLayout cardLayout;

    private MainPanel mainPanel;
    private CustomersPanel customersPanel;
    private SuppliersPanel suppliersPanel;
    private CalculatorPanel calculatorPanel;

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

        setupCustomersPanel();
        container.add(customersPanel);
        cardLayout.addLayoutComponent(customersPanel, CUSTOMERS_PANEL);

        setupSuppliersPanel();
        container.add(suppliersPanel);
        cardLayout.addLayoutComponent(suppliersPanel, SUPPLIERS_PANEL);

        setupCalculatorPanel();
        container.add(calculatorPanel);
        cardLayout.addLayoutComponent(calculatorPanel, CALCULATOR_PANEL);

    }

    // MODIFIES: this
    // EFFECTS: initialize and set up mainPanel
    private void setupMainPanel() {
        mainPanel = new MainPanel(container);
    }

    // MODIFIES: this
    // EFFECTS: initialize and set up customersPanel
    private void setupCustomersPanel() {
        customersPanel = new CustomersPanel(this.system, container);
        system.getCustomersMap().addObserver(customersPanel);
    }

    // MODIFIES: this
    // EFFECTS: initialize and set up suppliersPanel
    private void setupSuppliersPanel() {
        suppliersPanel = new SuppliersPanel(this.system, container);
        system.getSuppliersMap().addObserver(suppliersPanel);
    }

    // MODIFIES: this
    // EFFECTS: initialize and set up calculatorPanel
    private void setupCalculatorPanel() {
        calculatorPanel = new CalculatorPanel(this.system, container);
        system.getCustomersMap().addObserver(calculatorPanel);
        system.getSuppliersMap().addObserver(calculatorPanel);
    }

    public JPanel getContainer() {
        return container;
    }

}
