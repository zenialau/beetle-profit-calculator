package src.main.ui.gui.traders.window;

import src.main.ui.gui.GUISystem;

import javax.swing.*;
import java.awt.*;

public abstract class AddTraderWindow {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 300;

    protected GUISystem system;

    protected JFrame frame;
    protected JPanel panel;
    protected SpringLayout springLayout;

    protected JButton addTraderButton;
    protected JLabel duplicateTraderLabel;

    public AddTraderWindow(GUISystem system) {
        this.system = system;
    }

    // MODIFIES: this
    // EFFECTS: add the duplicateBuyerLabel to the panel
    protected void addDuplicateLabel(String trader) {
        duplicateTraderLabel = new JLabel(trader + " already exists (same IG account).");
        duplicateTraderLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        duplicateTraderLabel.setForeground(Color.RED);
        duplicateTraderLabel.setVisible(false);
        springLayout.putConstraint(SpringLayout.EAST, duplicateTraderLabel, -10, SpringLayout.WEST, addTraderButton);
        springLayout.putConstraint(SpringLayout.SOUTH, duplicateTraderLabel, -35, SpringLayout.SOUTH, panel);
        panel.add(duplicateTraderLabel);
    }

    // MODIFIES: this
    // EFFECTS: add the Add Buyer button to the panel
    protected void setupAddTraderButton(String trader) {
        addTraderButton = new JButton("Add " + trader);
        springLayout.putConstraint(SpringLayout.EAST, addTraderButton, -50, SpringLayout.EAST, panel);
        springLayout.putConstraint(SpringLayout.SOUTH, addTraderButton, -30, SpringLayout.SOUTH, panel);
    }

    // MODIFIES: this
    // EFFECTS: set up panel
    protected void setupPanel() {
        panel = new JPanel();
        springLayout = new SpringLayout();
        panel.setLayout(springLayout);
    }

    // MODIFIES: this
    // EFFECTS: set up the specified field below elementAbove
    protected void setupTextField(JTextField field, java.awt.Component elementAbove) {
        field.setPreferredSize(new Dimension(500, 25));
        springLayout.putConstraint(SpringLayout.WEST, field, 150, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, field, 50, SpringLayout.NORTH, elementAbove);
    }

    // MODIFIES: this
    // EFFECTS: set up label (with respect to text fields) in respect to elementRight and elementAbove
    protected void setupLabel(JLabel label, java.awt.Component elementRight, java.awt.Component elementAbove) {
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        springLayout.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.WEST, elementRight);
        springLayout.putConstraint(SpringLayout.NORTH, label, 50, SpringLayout.NORTH, elementAbove);
    }

    // MODIFIES: this
    // EFFECTS: set up JFrame window
    protected void setupFrame() {
        frame = new JFrame();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center of screen
    }

}
