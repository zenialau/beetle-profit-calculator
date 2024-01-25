package src.main.ui.gui;

import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.inventory.Purchase;
import src.main.model.oberver.BuyerObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.main.ui.gui.PanelsContainer.MAIN_PANEL;

// buyers screen that shows existing buyers and allow the option to add buyer or purchase, or go back to main menu
public class BuyersPanel extends JPanel implements ActionListener, BuyerObserver {
    private static final float CENTER = 0.5F;
    private static final int BUYERS_BUTTON_HEIGHT = 50;

    private GUISystem system;

    private JPanel container;
    private CardLayout cardLayout;

    private JLabel titleLabel;

    private JPanel buyersContainer;
    private JScrollPane scrollPane;
    private int buyersContainerHeight;

    private JPanel buttonPanel;
    private JButton backButton;
    private JButton addBuyerButton;

    // EFFECTS: constructs the buyers panel
    public BuyersPanel(GUISystem system, JPanel container) {
        this.system = system;
        this.container = container;
        this.cardLayout = (CardLayout) container.getLayout();
        this.setLayout(new BorderLayout());

        addTitle();
        addSpace();
        addScrollPane();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: add "back" button and "add buyer" button
    private void addButtons() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setPreferredSize(new Dimension(600, 70));

        backButton = new JButton("< back");
        addBuyerButton = new JButton("Add Buyer");
        backButton.addActionListener(this);
        addBuyerButton.addActionListener(this);
        buttonPanel.add(backButton);
        buttonPanel.add(addBuyerButton);

        this.add(buttonPanel, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: add a scroll pane containing buttons of buyers
    private void addScrollPane() {
        buyersContainer = new JPanel();
        buyersContainer.setBackground(Color.blue);
        buyersContainer.setLayout(new BoxLayout(buyersContainer, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(buyersContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        addBuyers();
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
        System.out.println("make buyer panel for " + buyer.getName());

        SpecificBuyerPanel specPanel = new SpecificBuyerPanel(system, container, buyer);
        system.getBuyersMap().addObserver(specPanel);
        container.add(specPanel);
        cardLayout.addLayoutComponent(specPanel, buyer.getIgAccount());
    }

    // MODIFIES: this
    // EFFECTS: create specific ActionListener for specific button
    private void setActionListener(Buyer buyer, JButton button) {
        System.out.println("set action listener for " + buyer.getName());

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container, buyer.getIgAccount()); // igAccount as constraint
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: add space to the left and right of scroll pane
    private void addSpace() {
        JPanel leftSpace = new JPanel();
        JPanel rightSpace = new JPanel();
        leftSpace.setPreferredSize(new Dimension(100, 100));
        rightSpace.setPreferredSize(new Dimension(100, 100));
        this.add(leftSpace, BorderLayout.LINE_START);
        this.add(rightSpace, BorderLayout.LINE_END);
    }

    // MODIFIES: this
    // EFFECTS: add title label to this panel
    private void addTitle() {
        titleLabel = new JLabel("Buyers");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        titleLabel.setAlignmentX(CENTER);
        titleLabel.setPreferredSize(new Dimension(600, 50));
        this.add(titleLabel, BorderLayout.PAGE_START);
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
