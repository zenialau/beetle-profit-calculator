package src.main.ui.gui.traders;

import src.main.model.oberver.BuyerObserver;
import src.main.ui.gui.GUISystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class TradersPanel extends JPanel implements ActionListener, BuyerObserver {

    protected static final float CENTER = 0.5F;
    protected static final int BUYERS_BUTTON_HEIGHT = 50;

    protected GUISystem system;

    protected JPanel container;
    protected CardLayout cardLayout;

    protected JLabel titleLabel;

    protected JPanel buyersContainer;
    protected JScrollPane scrollPane;
    protected int buyersContainerHeight;

    protected JPanel buttonPanel;
    protected JButton backButton;
    protected JButton addBuyerButton;

    // EFFECTS: constructs the buyers panel
    public TradersPanel(GUISystem system, JPanel container) {
        this.system = system;
        this.container = container;
        this.cardLayout = (CardLayout) container.getLayout();
        this.setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: add "back" button and "add buyer" button
    protected void addButtons() {
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
    protected void addScrollPane() {
        buyersContainer = new JPanel();
        buyersContainer.setBackground(Color.lightGray);
        buyersContainer.setLayout(new BoxLayout(buyersContainer, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(buyersContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: add space to the left and right of scroll pane
    protected void addSpace() {
        JPanel leftSpace = new JPanel();
        JPanel rightSpace = new JPanel();
        leftSpace.setPreferredSize(new Dimension(100, 100));
        rightSpace.setPreferredSize(new Dimension(100, 100));
        this.add(leftSpace, BorderLayout.LINE_START);
        this.add(rightSpace, BorderLayout.LINE_END);
    }

    // MODIFIES: this
    // EFFECTS: add title label to this panel
    protected void addTitle(String title) {
        titleLabel = new JLabel("               " + title);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        titleLabel.setAlignmentX(CENTER);
        titleLabel.setPreferredSize(new Dimension(600, 50));
        this.add(titleLabel, BorderLayout.PAGE_START);
    }

}
