package src.main.ui.gui.traders.window;

import src.main.model.company.Supplier;
import src.main.model.company.SuppliersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.exception.InvalidTraderException;
import src.main.ui.gui.GUISystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// a new window that allows user to input values and add a new supplier
public class AddSupplierWindow extends AddTraderWindow implements ActionListener {

    private JLabel nameLabel;
    private JLabel countryLabel;
    private JTextField nameField;
    private JTextField countryField;

    // EFFECTS: constructs a pop-up window to add new supplier
    public AddSupplierWindow(GUISystem system) {
        super(system);
        setupFrame();
        setupPanel();
        addContent();

        setupAddTraderButton("Supplier");
        addTraderButton.addActionListener(this);
        panel.add(addTraderButton);

        addDuplicateLabel("Supplier");
        frame.add(panel);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: add content (labels and text fields) to panel
    private void addContent() {
        nameLabel = new JLabel("Name: ");
        nameField = new JTextField();
        countryLabel = new JLabel("Country: ");
        countryField = new JTextField();

        setupTextField(nameField, panel);
        setupTextField(countryField, nameField);

        setupLabel(nameLabel, nameField, panel);
        setupLabel(countryLabel, countryField, nameLabel);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(countryLabel);
        panel.add(countryField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            addSupplier();
        } catch (DuplicateException ex) {
            duplicateTraderLabel.setVisible(true);
        } catch (InvalidTraderException ex) {
            nameField.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    // MODIFIES: this.system.suppliers
    // EFFECTS: if supplier already exist or the nameField is empty, notify the user
    //          else add supplier to SuppliersMap
    private void addSupplier() throws InvalidTraderException, DuplicateException {
        Supplier newSupplier = new Supplier(nameField.getText(), countryField.getText());
        SuppliersMap suppliers = system.getSuppliersMap();
        if (nameField.getText().isBlank()) {
            throw new InvalidTraderException();
        } else {
            suppliers.addSupplier(newSupplier);
            frame.dispose();
        }
    }
}
