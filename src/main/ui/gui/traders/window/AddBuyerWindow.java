package src.main.ui.gui.traders.window;

import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.exception.InvalidTraderException;
import src.main.ui.gui.GUISystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// a new window that allows user to input values and add a new buyer
public class AddBuyerWindow extends AddTraderWindow implements ActionListener {

    private JLabel nameLabel;
    private JLabel igLabel;
    private JLabel addressLabel;
    private JTextField nameField;
    private JTextField igField;
    private JTextField addressField;

    // EFFECTS: constructs a pop-up window to add new buyer
    public AddBuyerWindow(GUISystem system) {
        super(system);
        setupFrame();
        setupPanel();
        addContent();

        setupAddTraderButton("Customer");
        addTraderButton.addActionListener(this);
        panel.add(addTraderButton);

        addDuplicateLabel("Customer");
        frame.add(panel);
        frame.setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            addBuyer();
            System.out.println("Buyer successfully added.");
        } catch (DuplicateException ex) {
            System.out.println("Buyer already exists.");
            duplicateTraderLabel.setVisible(true);
        } catch (InvalidTraderException ex) {
            System.out.println("nameField is empty.");
            nameField.setBorder(BorderFactory.createLineBorder(Color.RED)); // set nameField border to red
        }
    }

    // MODIFIES: this.system.buyers
    // EFFECTS: if buyer already exist or the nameField is empty, notify the user
    //          else add buyer to BuyersMap
    private void addBuyer() throws DuplicateException, InvalidTraderException {
        Buyer newBuyer = new Buyer(nameField.getText(), igField.getText(), addressField.getText());

        BuyersMap buyers = system.getBuyersMap();

        if (nameField.getText().isBlank()) {
            throw new InvalidTraderException();
        } else {
            buyers.addBuyer(newBuyer);
            frame.dispose();
        }
    }

}
