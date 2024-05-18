package src.main.ui.gui.traders.specific;

import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.ui.gui.GUISystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.time.LocalDate;

import static src.main.ui.gui.GUISystem.PLAIN_18_FONT;
import static src.main.ui.gui.GUISystem.TITLE_FONT;

public class GenericSpecificPanel extends JPanel {
    protected static final float CENTER = 0.5F;
    private static final String[] COLUMN_NAMES = {"Date", "Name", "Description", "Size (mm)",
            "Quality", "Comment", "Price (CAD)"};

    protected PurchaseList purchaseList;

    protected GUISystem system;
    protected JPanel container;
    protected CardLayout cardLayout;

    protected JLabel titleLabel;
    protected JPanel purchasesContainer;
    protected JScrollPane scrollPane;

    protected JTable purchasesTable;
    protected Object[][] data;

    protected JPanel buttonPanel;
    protected JButton backButton;
    protected JButton addPurchaseButton;

    public GenericSpecificPanel(GUISystem system, JPanel container) {
        this.system = system;
        this.container = container;
        this.cardLayout = (CardLayout) container.getLayout();
        this.setLayout(new BorderLayout());
    }

    // MODIFIES: this
    // EFFECTS: set up title label and add it to this
    protected void setupTitle(JLabel titleLabel) {
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setAlignmentX(CENTER);
        titleLabel.setPreferredSize(new Dimension(600, 50));
        this.add(titleLabel, BorderLayout.PAGE_START);
    }

    // MODIFIES: this
    // EFFECTS: add space to the left and right of scroll pane
    protected void addSpace() {
        JPanel leftSpace = new JPanel();
        JPanel rightSpace = new JPanel();
        leftSpace.setPreferredSize(new Dimension(50, 100));
        rightSpace.setPreferredSize(new Dimension(50, 100));
        this.add(leftSpace, BorderLayout.LINE_START);
        this.add(rightSpace, BorderLayout.LINE_END);
    }

    // MODIFIES: this
    // EFFECTS: add the scroll pane (that will contain the purchase list) to this
    protected void addScrollPane() {
        purchasesContainer = new JPanel();
        purchasesContainer.setBackground(Color.lightGray);
        purchasesContainer.setLayout(new BoxLayout(purchasesContainer, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(purchasesContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES; this
    // EFFECTS: add PurchaseList of buyer to scrollPane
    protected void addPurchaseList() {
        initData();
        purchasesTable = new JTable(data, COLUMN_NAMES);
        setupTable();
        purchasesContainer.add(purchasesTable);
    }

    // MODIFIES: this
    // EFFECTS: set up purchaseTable
    private void setupTable() {
        purchasesTable.setFont(PLAIN_18_FONT);
        purchasesTable.setRowHeight(25);
        //setColumnWidth();
        setNotEditable();
    }

    // MODIFIES: this
    // EFFECTS: set specific column widths of table
    private void setColumnWidth() { //!!! not changing anything
        TableColumn column = null;
        for (int i = 0; i < purchasesTable.getColumnCount(); i++) {
            column = purchasesTable.getColumnModel().getColumn(i);
            if (i == 5) {
                column.setPreferredWidth(200); //sixth column is bigger
            } else {
                column.setPreferredWidth(50);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: make purchasesTable not editable
    private void setNotEditable() {
        DefaultTableModel tableModel = new DefaultTableModel(data, COLUMN_NAMES) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //all cells false
            }
        };
        purchasesTable.setModel(tableModel);
    }

    // MODIFIES: this
    // EFFECTS: initialize data for creating JTable
    private void initData() {
        data = new Object[purchaseList.getNumItems()+1][7];
        data[0][0] = "Date";
        data[0][1] = "Name";
        data[0][2] = "Description";
        data[0][3] = "Size(mm)";
        data[0][4] = "Quality";
        data[0][5] = "Comment";
        data[0][6] = "Price(CAD)";

        int roll = 1;

        for (Purchase purchase : purchaseList) {
            LocalDate date = purchase.getPurchaseDate();
            data[roll][0] = date.toString();

            for (InventoryItem item : purchase) {
                data[roll][1] = item.getName();
                data[roll][2] = item.getDescription();
                data[roll][3] = item.getSize();
                data[roll][4] = item.getQuality();
                data[roll][5] = item.getComment();
                data[roll][6] = item.getPrice();
                roll++;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: set up buttonPanel
    protected void setupButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setPreferredSize(new Dimension(600, 70));
    }

    // MODIFIES: this
    // EFFECTS: set up addPurchaseButton
    protected void setupAddPurchaseButton() {
        addPurchaseButton = new JButton("Add Purchase");
//        addPurchaseButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: finish set up buttonPanel
    protected void finishButtonPanel() {
        buttonPanel.add(backButton);
        buttonPanel.add(addPurchaseButton);
        this.add(buttonPanel, BorderLayout.PAGE_END);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        new AddPurchaseWindow(system, buyer);
//    }
}
