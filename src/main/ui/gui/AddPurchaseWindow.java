package src.main.ui.gui;

import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.exception.InvalidItemException;
import src.main.model.exception.NoItemException;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

import static src.main.ui.gui.GUISystem.PLAIN_20_FONT;

// a new window that allows user to input values and add a new purchase to the buyer specified
public class AddPurchaseWindow implements ActionListener {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 300;

    private GUISystem system;
    private Buyer buyer;

    private JFrame frame;
    private JPanel panel;
    private SpringLayout springLayout;
    private JLabel dateLabel;
    private JTextField dateField;
    private JScrollPane scrollPane;
    private DefaultTableModel inputTableModel;
    private JTable inputTable;
    private JButton addRowButton;
    private JButton addPurchaseButton;

    // EFFECTS: constructs a popup window to add new purchase to buyer
    public AddPurchaseWindow(GUISystem system, Buyer buyer) {
        this.system = system;
        this.buyer = buyer;
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
        addDateField();
        addInputTable();
        addAddRowButton();
        addAddPurchaseButton();
        frame.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: add dateLabel and dateField to panel
    private void addDateField() {
        dateLabel = new JLabel("Purchase date: [YYYY-MM-DD]");
        dateLabel.setFont(PLAIN_20_FONT);
        springLayout.putConstraint(SpringLayout.WEST, dateLabel, 30, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, dateLabel, 20, SpringLayout.NORTH, panel);
        panel.add(dateLabel);

        dateField = new JTextField("YYYY-MM-DD");
        dateField.setPreferredSize(new Dimension(200, 20));
        springLayout.putConstraint(SpringLayout.WEST, dateField, 5, SpringLayout.EAST, dateLabel);
        springLayout.putConstraint(SpringLayout.NORTH, dateField, 20, SpringLayout.NORTH, panel);
        panel.add(dateField);
    }

    // MODIFIES: this
    // EFFECTS: add input table for purchase
    private void addInputTable() {
        String[] colNames = {"Name", "Description", "Size (mm)",
                "Quality", "Comment", "Price (CAD)"};
        inputTableModel = new DefaultTableModel(colNames, 1) {
            @Override
            public String getColumnName(int index) {
                return colNames[index];
            }
        };
        inputTable = new JTable(inputTableModel);
        inputTable.setRowHeight(25);

        scrollPane = new JScrollPane(inputTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(WINDOW_WIDTH-60, 150));

        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 30, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.SOUTH, dateLabel);

        panel.add(scrollPane);
    }

    // MODIFIES: this
    // EFFECTS: add the Add row button to the panel
    private void addAddRowButton() {
        addRowButton = new JButton("+");
        addRowButton.setPreferredSize(new Dimension(15, 15));
        addRowButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.WEST, addRowButton, 0, SpringLayout.EAST, scrollPane);
        springLayout.putConstraint(SpringLayout.NORTH, addRowButton, 195, SpringLayout.NORTH, panel);
        panel.add(addRowButton);
    }

    // MODIFIES: this
    // EFFECTS: add the Add Buyer button to the panel
    private void addAddPurchaseButton() {
        addPurchaseButton = new JButton("Add Purchase");
        addPurchaseButton.addActionListener(this);
        springLayout.putConstraint(SpringLayout.EAST, addPurchaseButton, -30, SpringLayout.EAST, panel);
        springLayout.putConstraint(SpringLayout.SOUTH, addPurchaseButton, -20, SpringLayout.SOUTH, panel);
        panel.add(addPurchaseButton);
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
        if (e.getSource() == addRowButton) {
            addRow();
        } else if (e.getSource() == addPurchaseButton) {
            try {
                addPurchase();
            } catch (DateTimeParseException ex) {
                System.out.println("Please enter a valid date according to the format provided.");
                // when the date input is not valid
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input values for size or price!");
                // when size or price have non-numeric input values
            } catch (InvalidItemException ex) {
                System.out.println("Invalid item! Item needs at least a name and a price!");
                //...
            } catch (NoItemException ex) {
                System.out.println("There are no items in this purchase.");
                //...
            }
//            catch (ClassCastException ex) { // when try to parse price input to double
//                System.out.println("Double check input values.");
//            }
        }
    }

    // MODIFIES: this
    // EFFECTS: parse the user inputted data into a purchase and add it to the corresponding buyer (this.buyer)
    private void addPurchase() throws DateTimeParseException,InvalidItemException, NoItemException {
        BuyersMap buyersMap = system.getBuyersMap();
        Purchase purchase = new Purchase();

        // 1. check that dateField is not empty (or just try catch), blank also throw DateTimeParseException
        //    -> make dateField border red if empty
        // 2. try to set purchase date (might be wrong format)
        //    -> if caught DateTimeParseException, make dateField border red AND show warning label
        //    -> "Enter a valid date according to the format provided."
        String dateInput = dateField.getText();
        purchase.setPurchaseDate(dateInput); //throws DateTimeParseException if invalid

        // 3. make items according to user input values
        //    (only create item if both name and price are present in that row)
        //    -> inputTable.getModel().getValueAt(row_index, col_index);
        //    -> model.getValueAt(row_index, col_index);
        int rowNum = inputTableModel.getRowCount();
        System.out.println(rowNum);
        for (int r = 0; r < rowNum; r++) {
            String name = (String) inputTableModel.getValueAt(r, 0);
            String desc = (String) inputTableModel.getValueAt(r, 1);

            String sizeInput = (String) inputTableModel.getValueAt(r, 2);
            Double size = null;
            if (!isNullOrBlank(sizeInput)) {
                size = Double.parseDouble(sizeInput); // throws NumberFormatException
            }

            String quality = (String) inputTableModel.getValueAt(r, 3);
            String comment = (String) inputTableModel.getValueAt(r, 4);

            String priceInput = (String) inputTableModel.getValueAt(r, 5);
            Double price = null;
            if (!isNullOrBlank(priceInput)) {
                price = Double.parseDouble(priceInput); // throws NumberFormatException
            }

            System.out.println(name);
            System.out.println(desc);
            System.out.println(size);
            System.out.println(quality);
            System.out.println(comment);
            System.out.println(price);

            if (!isEmptyRow(r)) {
                if (isNullOrBlank(name) || isNullOrBlank(priceInput)) {
                    throw new InvalidItemException();
                }
                if (size == null) { // set size as a double so that item could be constructed
                    size = 0.0;
                }
                InventoryItem item = new InventoryItem(name, desc, size, quality, comment, price);
                purchase.addItem(item);
            }
        }

        // 4. add created items to purchase
        //    -> if 0 items, throw NoItemException
        if (purchase.isEmpty()) {
            throw new NoItemException();
        }

        // 5. add purchase to buyersMap for this.buyer
        buyersMap.addPurchase(this.buyer, purchase);
        System.out.println("purchase succuessfully added!");
        //dispose window??
    }

    // EFFECTS: return true if all the inputs in the given row is empty/blank
    private boolean isEmptyRow(int rowNum) {
        boolean isEmpty = true;
        for (int i = 0; i < 6; i++) {
            String input = (String) inputTableModel.getValueAt(rowNum, i);
            if (!isNullOrBlank(input)) {
                isEmpty = false;
            }
        }
        return isEmpty;
    }

    // EFFECTS: return true if the given string is null or blank
    private boolean isNullOrBlank(String s) {
        return s == null || s.isBlank();
    }

    // MODIFIES: this
    // EFFECTS: add an extra row to inputTable for user to add another item to the purchase
    private void addRow() {
        DefaultTableModel model = (DefaultTableModel) inputTable.getModel();
        model.addRow(new Object[] {null, null, null, null, null, null});
    }

}
