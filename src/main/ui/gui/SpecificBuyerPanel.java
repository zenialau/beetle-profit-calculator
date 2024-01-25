package src.main.ui.gui;

import src.main.model.company.Buyer;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;
import src.main.model.oberver.PurchaseObserver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import static src.main.ui.gui.GUISystem.PLAIN_18_FONT;
import static src.main.ui.gui.GUISystem.TITLE_FONT;
import static src.main.ui.gui.PanelsContainer.BUYERS_PANEL;

public class SpecificBuyerPanel extends JPanel implements ActionListener, PurchaseObserver {
    private static final float CENTER = 0.5F;
    private static final String[] COLUMN_NAMES = {"Date", "Name", "Description", "Size (mm)",
                                                    "Quality", "Comment", "Price (CAD)"};

    private GUISystem system;
    private JPanel container;
    private CardLayout cardLayout;
    private Buyer buyer;
    private PurchaseList purchaseList;

    private JLabel titleLabel;

    private JPanel purchasesContainer;
    private JScrollPane scrollPane;
    private JTable purchasesTable;
    private Object[][] data;

    private JPanel buttonPanel;
    private JButton backButton;
    private JButton addPurchaseButton;


    public SpecificBuyerPanel(GUISystem system, JPanel container, Buyer buyer) {
        this.system = system;
        this.container = container;
        this.cardLayout = (CardLayout) container.getLayout();
        this.buyer = buyer;
        this.purchaseList = system.getBuyersMap().getPurchaseList(buyer);

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
        addPurchaseButton = new JButton("Add Purchase");
        backButton.addActionListener(this);
        addPurchaseButton.addActionListener(this);
        buttonPanel.add(backButton);
        buttonPanel.add(addPurchaseButton);

        this.add(buttonPanel, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: add the PurchaseList of buyer as a table
    private void addScrollPane() {
        purchasesContainer = new JPanel();
        purchasesContainer.setBackground(Color.blue);
        purchasesContainer.setLayout(new BoxLayout(purchasesContainer, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(purchasesContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        addPurchaseList();
    }

    // MODIFIES; this
    // EFFECTS: add PurchaseList of buyer to scrollPane
    private void addPurchaseList() {
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
    // EFFECTS: add space to the left and right of scroll pane
    private void addSpace() {
        JPanel leftSpace = new JPanel();
        JPanel rightSpace = new JPanel();
        leftSpace.setPreferredSize(new Dimension(50, 100));
        rightSpace.setPreferredSize(new Dimension(50, 100));
        this.add(leftSpace, BorderLayout.LINE_START);
        this.add(rightSpace, BorderLayout.LINE_END);
    }

    // MODIFIES: this
    // EFFECTS: add title label to this panel
    private void addTitle() { //!!! also display igAccount and address
        titleLabel = new JLabel(buyer.getName());
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setAlignmentX(CENTER);
        titleLabel.setPreferredSize(new Dimension(600, 50));
        this.add(titleLabel, BorderLayout.PAGE_START);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(container, BUYERS_PANEL);
        } else if (e.getSource() == addPurchaseButton) {
            new AddPurchaseWindow(system, buyer);
        }
    }

    @Override
    public void update() {
        purchasesContainer.remove(purchasesTable);
        addPurchaseList();
        purchasesContainer.setVisible(false); // such that the display updates right away
        purchasesContainer.setVisible(true);
    }

}
