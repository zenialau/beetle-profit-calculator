package src.main.ui.gui.traders.specific;

import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.company.Trader;
import src.main.model.company.TradersMap;
import src.main.model.exception.InvalidItemException;
import src.main.model.exception.NoItemException;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;
import src.main.ui.gui.GUISystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

import static src.main.ui.gui.GUISystem.PLAIN_18_FONT;

// a new window that allows user to input values and add a new purchase to the buyer specified
public class AddPurchaseWindowGeneral implements ActionListener {

     private static final int WINDOW_WIDTH = 800;
     private static final int WINDOW_HEIGHT = 300;

     private GUISystem system;
     private Trader trader;
     private String type;

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

     private JLabel noItemsWarning;
     private JLabel invalidInputWarning;
     private JLabel invalidItemWarning;

     // EFFECTS: constructs a popup window to add new purchase to buyer
     public AddPurchaseWindowGeneral(GUISystem system, Trader trader, String type) {
          this.system = system;
          this.trader = trader;
          this.type = type;
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
          addWarningLabels();
          frame.add(panel);
     }

     // MODIFIES: this
     // EFFECTS: add dateLabel and dateField to panel
     private void addDateField() {
          dateLabel = new JLabel("Purchase date: [YYYY-MM-DD]");
          dateLabel.setFont(PLAIN_18_FONT);
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
     // EFFECTS: add warning labels to panel
     private void addWarningLabels() {
          noItemsWarning = new JLabel("bruh empty purchase??");
          invalidInputWarning = new JLabel("uhhhh enter a NUMBER for size/price");
          invalidItemWarning = new JLabel("at least gimme a name and price");

          setupWarningLabel(noItemsWarning);
          setupWarningLabel(invalidInputWarning);
          setupWarningLabel(invalidItemWarning);

          panel.add(noItemsWarning);
          panel.add(invalidInputWarning);
          panel.add(invalidItemWarning);
     }

     // MODIFIES: this
     // EFFECTS: set up the warning labels
     private void setupWarningLabel(JLabel label) {
          label.setFont(PLAIN_18_FONT);
          label.setForeground(Color.RED);
          springLayout.putConstraint(SpringLayout.WEST, label, 30, SpringLayout.WEST, panel);
          springLayout.putConstraint(SpringLayout.SOUTH, label, -25, SpringLayout.SOUTH, panel);
          label.setVisible(false);
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
               resetWarnings();
               try {
                    addPurchase();
               } catch (DateTimeParseException ex) {
                    dateField.setBorder(BorderFactory.createLineBorder(Color.RED));
               } catch (NumberFormatException ex) {
                    invalidInputWarning.setVisible(true);
               } catch (InvalidItemException ex) {
                    invalidItemWarning.setVisible(true);
               } catch (NoItemException ex) {
                    noItemsWarning.setVisible(true);
               }
          }
     }

     // EFFECTS: reset the labels and fields to default
     private void resetWarnings() {
          dateField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
          noItemsWarning.setVisible(false);
          invalidInputWarning.setVisible(false);
          invalidItemWarning.setVisible(false);
     }

     // MODIFIES: this
     // EFFECTS: parse the user inputted data into a purchase and add it to the corresponding buyer (this.buyer)
     private void addPurchase() throws DateTimeParseException,InvalidItemException, NoItemException {
          TradersMap tm;
          if (type == "b") tm = system.getBuyersMap();
          else tm = system.getSuppliersMap();
          Purchase purchase = new Purchase();

          setPurchaseDate(purchase);
          addItemsToPurchase(purchase);
          if (purchase.isEmpty()) {
               throw new NoItemException();
          }
          tm.addPurchase(this.trader, purchase);

          frame.dispose();
     }

     // MODIFIES: purchase
     // EFFECTS: constructs items according to user input values and add them to purchase
     //          throws InvalidItemException if a non-empty row doesn't contain a name or a price
     //          throws NumberFormatException if something other than a double is entered for size or price
     private void addItemsToPurchase(Purchase purchase) throws InvalidItemException, NumberFormatException {
          int rowNum = inputTableModel.getRowCount();
          for (int r = 0; r < rowNum; r++) {
               String name = getUserInputString(r, 0);
               String desc = getUserInputString(r, 1);
               Double size = getUserInputDouble(r, 2);
               String quality = getUserInputString(r, 3);
               String comment = getUserInputString(r, 4);
               Double price = getUserInputDouble(r, 5);

               String priceInput = getUserInputString(r, 5);
               if (!isEmptyRow(r)) { // add item if row is not empty
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
     }

     // EFFECTS: return a String representation of the user inputted value at (row, column)
     private String getUserInputString(int row, int column) {
          String input = (String) inputTableModel.getValueAt(row, column);
          return input;
     }

     private Double getUserInputDouble(int row, int column) throws NumberFormatException {
          String numInput = getUserInputString(row, column);
          Double num = null;
          if (!isNullOrBlank(numInput)) {
               num = Double.parseDouble(numInput); // throws NumberFormatException
          }
          return num;
     }

     // MODIFIES: purchase
     // EFFECTS: if the date inputted is invalid or blank, throw DateTimeParseException, else set purchase date
     private void setPurchaseDate(Purchase purchase) throws DateTimeParseException {
          String dateInput = dateField.getText();
          purchase.setPurchaseDate(dateInput);
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
