package src.main.ui.gui.traders;

import src.main.ui.gui.GUISystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// abstract parent class of BuyersPanel and SuppliersPanel
public abstract class TradersPanel extends JPanel implements ActionListener {

    protected static final float CENTER = 0.5F;
    protected static final int BUYERS_BUTTON_HEIGHT = 50;
    protected static final int BUTTON_WIDTH = 700;
    protected static final int CONTAINER_WIDTH = 650;

    protected GUISystem system;

    protected JPanel container;
    protected CardLayout cardLayout;

    protected JLabel titleLabel;

    protected JPanel tradersContainer;
    protected JScrollPane scrollPane;
    protected int tradersContainerHeight;

    protected JPanel buttonPanel;
    protected JButton backButton;
    protected JButton addTraderButton;

    // EFFECTS: constructs the buyers panel
    public TradersPanel(GUISystem system, JPanel container) {
        this.system = system;
        this.container = container;
        this.cardLayout = (CardLayout) container.getLayout();
        this.setLayout(new BorderLayout());
    }

    protected void setButtonDimensions(JButton button) {
        button.setMaximumSize(new Dimension(BUTTON_WIDTH, BUYERS_BUTTON_HEIGHT));
    }

    // MODIFIES: this
    // EFFECTS: add "back" button and "add buyer" button
    protected void addButtons(String trader) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setPreferredSize(new Dimension(600, 70));

        backButton = new JButton("< back");
        addTraderButton = new JButton("Add " + trader);
        backButton.addActionListener(this);
        addTraderButton.addActionListener(this);
        buttonPanel.add(backButton);
        buttonPanel.add(addTraderButton);

        this.add(buttonPanel, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: add a scroll pane containing buttons of buyers
    protected void addScrollPane() {
        tradersContainer = new JPanel();
        tradersContainer.setBackground(Color.lightGray);
        tradersContainer.setLayout(new BoxLayout(tradersContainer, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(tradersContainer);
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
