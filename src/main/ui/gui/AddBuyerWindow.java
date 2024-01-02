package src.main.ui.gui;

import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.exception.InvalidBuyerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// a new window that allows user to input values and add a new buyer
public class AddBuyerWindow implements ActionListener {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 300;

    private GUISystem system;

    private JFrame frame;
    private JPanel panel;
    private SpringLayout springLayout;
    private JLabel nameLabel;
    private JLabel igLabel;
    private JLabel addressLabel;
    private JTextField nameField;
    private JTextField igField;
    private JTextField addressField;
    private JButton addBuyerButton;
    private JLabel duplicateBuyerLabel;

    // EFFECTS: constructs a popup window to add new buyer
    public AddBuyerWindow(GUISystem system) {
        this.system = system;
        setupFrame();
        setupPanel();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: set up panel
    private void setupPanel() {
        panel = new JPanel();
        springLayout = new SpringLayout();
        panel.setLayout(springLayout);
        addContent();
        addAddBuyerButton();
        addDuplicateLabel();
        frame.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: add the duplicateBuyerLabel to the panel
    private void addDuplicateLabel() {
        duplicateBuyerLabel = new JLabel("Buyer already exists (same IG account).");
        duplicateBuyerLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        duplicateBuyerLabel.setForeground(Color.RED);
        duplicateBuyerLabel.setVisible(false);
        springLayout.putConstraint(SpringLayout.EAST, duplicateBuyerLabel, -10, SpringLayout.WEST, addBuyerButton);
        springLayout.putConstraint(SpringLayout.SOUTH, duplicateBuyerLabel, -35, SpringLayout.SOUTH, panel);
        panel.add(duplicateBuyerLabel);
    }

    // MODIFIES: this
    // EFFECTS: add the Add Buyer button to the panel
    private void addAddBuyerButton() {
        addBuyerButton = new JButton("Add Buyer");
        addBuyerButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.EAST, addBuyerButton, -50, SpringLayout.EAST, panel);
        springLayout.putConstraint(SpringLayout.SOUTH, addBuyerButton, -30, SpringLayout.SOUTH, panel);
        panel.add(addBuyerButton);
    }

    // MODIFIES: this
    // EFFECTS: add content (labels and text fields) to panel
    private void addContent() {
        nameLabel = new JLabel("Name: ");
        nameField = new JTextField();
        igLabel = new JLabel("IG account: ");
        igField = new JTextField();
        addressLabel = new JLabel("Address: ");
        addressField = new JTextField();

        setupTextField(nameField, panel);
        setupTextField(igField, nameField);
        setupTextField(addressField, igField);

        setupLabel(nameLabel, nameField, panel);
        setupLabel(igLabel, igField, nameLabel);
        setupLabel(addressLabel, addressField, igLabel);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(igLabel);
        panel.add(igField);
        panel.add(addressLabel);
        panel.add(addressField);
    }

    // MODIFIES: this
    // EFFECTS: set up the specified field below elementAbove
    private void setupTextField(JTextField field, java.awt.Component elementAbove) {
        field.setPreferredSize(new Dimension(500, 25));
        springLayout.putConstraint(SpringLayout.WEST, field, 150, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, field, 50, SpringLayout.NORTH, elementAbove);
    }

    // MODIFIES: this
    // EFFECTS: set up label (with respect to text fields) in respect to elementRight and elementAbove
    private void setupLabel(JLabel label, java.awt.Component elementRight, java.awt.Component elementAbove) {
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        springLayout.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.WEST, elementRight);
        springLayout.putConstraint(SpringLayout.NORTH, label, 50, SpringLayout.NORTH, elementAbove);
    }

    // MODIFIES: this
    // EFFECTS: set up JFrame window
    private void setupFrame() {
        frame = new JFrame();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center of screen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            addBuyer();
            System.out.println("Buyer successfully added.");
        } catch (DuplicateException ex) {
            System.out.println("Buyer already exists.");
            duplicateBuyerLabel.setVisible(true);
        } catch (InvalidBuyerException ex) {
            System.out.println("nameField is empty.");
            nameField.setBorder(BorderFactory.createLineBorder(Color.RED)); // set nameField border to red
        }
    }

    private void addBuyer() throws DuplicateException, InvalidBuyerException {
        Buyer newBuyer = new Buyer(nameField.getText(), igField.getText(), addressField.getText());

        BuyersMap buyers = system.getBuyersMap();

        if (nameField.getText().isBlank()) {
            throw new InvalidBuyerException();
        } else {
            buyers.addBuyer(newBuyer);
        }
    }

}
