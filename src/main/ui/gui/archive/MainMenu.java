//package src.main.ui.gui;
//
//import src.main.model.company.BuyersMap;
//import src.main.model.company.SuppliersMap;
//import src.main.persistence.BuyersJsonReader;
//import src.main.persistence.JsonWriter;
//import src.main.persistence.SuppliersJsonReader;
//import src.main.ui.Main;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//// Main menu of GUI
//public class MainMenu extends JFrame implements ActionListener {
//
////    protected static final String BUYERS_JSON_STORE = "./data/buyers.json";
////    protected static final String SUPPLIERS_JSON_STORE = "./data/suppliers.json";
//    public static final int SCREEN_WIDTH = 900;
//    public static final int SCREEN_HEIGHT = 600;
//    protected static final float CENTER = 0.5F;
//
////    protected BuyersMap buyers;
////    protected SuppliersMap suppliers;
////    protected BuyersJsonReader buyersReader;
////    protected SuppliersJsonReader suppliersReader;
////    protected JsonWriter buyersWriter;
////    protected JsonWriter suppliersWriter;
//
//    private CardLayout cardLayout;
//
//    private JFrame frame;
//    private JPanel container;
//
//    private JPanel mainPanel;
//
////    private JMenuBar menuBar;
////    private JMenu fileMenu;
////    private JMenuItem loadItem;
////    private JMenuItem saveItem;
//
//    private JLabel titleLabel;
//    private JButton buyersButton;
//    private JButton suppliersButton;
//    private JButton calculatorButton;
//
//    private JPanel buyersPanel;
//
//    // EFFECTS: constructs main menu gui
//    public MainMenu() {
//        //initPersistence();
//        initData();
//        //setupFrame();
//        frame = new MainFrame();
//        setupPanels();
//        frame.add(container);
//        frame.setVisible(true);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: add the 3 main buttons to panel
//    private void addButtons() {
//        Dimension maxDimension = new Dimension(300, 60);
//
//        buyersButton = new JButton("Buyers");
//        suppliersButton = new JButton("Suppliers");
//        calculatorButton = new JButton("Profit Calculator");
//
//        buyersButton.setFont(new Font("Serif", Font.PLAIN, 20));
//        suppliersButton.setFont(new Font("Serif", Font.PLAIN, 20));
//        calculatorButton.setFont(new Font("Serif", Font.PLAIN, 20));
//
//        buyersButton.setMaximumSize(maxDimension);
//        suppliersButton.setMaximumSize(maxDimension);
//        calculatorButton.setMaximumSize(maxDimension);
//
//        buyersButton.setAlignmentX(CENTER);
//        suppliersButton.setAlignmentX(CENTER);
//        calculatorButton.setAlignmentX(CENTER);
//
//        buyersButton.setFocusPainted(false);
//        suppliersButton.setFocusPainted(false);
//        calculatorButton.setFocusPainted(false);
//
//        buyersButton.addActionListener(this);
//        suppliersButton.addActionListener(this);
//        calculatorButton.addActionListener(this);
//
//        mainPanel.add(buyersButton);
//        mainPanel.add(suppliersButton);
//        mainPanel.add(calculatorButton);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: add application title and logo to panel
//    private void addTitle() {
//        titleLabel = new JLabel("etern_mology");
//
//        titleLabel.setFont(new Font("Serif", Font.PLAIN, 26));
//        titleLabel.setAlignmentX(CENTER);
//
//        mainPanel.add(titleLabel);
//    }
//
////    // MODIFIES: this
////    // EFFECTS: add menu bar for save and load to the top of panel
////    private void addMenuBar() {
////        menuBar = new JMenuBar();
////        fileMenu = new JMenu("File");
////
////        loadItem = new JMenuItem("Load");
////        saveItem = new JMenuItem("Save");
////        loadItem.addActionListener(this);
////        saveItem.addActionListener(this);
////        loadItem.setMnemonic(KeyEvent.VK_L);
////        saveItem.setMnemonic(KeyEvent.VK_S);
////        saveItem.setAccelerator(KeyStroke.getKeyStroke("control S"));
////
////        fileMenu.add(loadItem);
////        fileMenu.add(saveItem);
////
////        menuBar.add(fileMenu);
////        frame.setJMenuBar(menuBar);
////    }
//
//    // MODIFIES: this
//    // EFFECTS: add rigid space between elements
//    private void addSpace(int width, int height) {
//        Dimension spaceDimension = new Dimension(width, height);
//        Component space = Box.createRigidArea(spaceDimension);
//        mainPanel.add(space);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: set up panels in container
//    private void setupPanels() {
//        container = new JPanel();
//        cardLayout = new CardLayout();
//        container.setLayout(cardLayout);
//        setupMainPanel();
//        setupBuyersPanel();
//        //...
//        container.add(mainPanel);
//        container.add(buyersPanel);
//        cardLayout.addLayoutComponent(mainPanel, "mainPanel");
//        cardLayout.addLayoutComponent(buyersPanel, "buyersPanel");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initialize and set up buyersPanel
//    private void setupBuyersPanel() {
//        buyersPanel = new BuyersPanel();
////        buyersPanel = new JPanel();
////        buyersPanel.setLayout(new BoxLayout(buyersPanel, BoxLayout.Y_AXIS));
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initialize and set up mainPanel
//    private void setupMainPanel() {
//        //mainPanel = new MainPanel();
//
//        mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        addSpace(1, 60);
//        addTitle();
//        addSpace(1, 60);
//        addButtons();
//    }
//
////    // MODIFIES: this
////    // EFFECTS: set up JFrame window
////    private void setupFrame() {
////        frame = new JFrame();
////        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        frame.setLocationRelativeTo(null); // center of screen
////        addMenuBar();
////    }
//
////    // MODIFIES: this
////    // EFFECTS: read previously saved data, or initialize new BuyersMap and SuppliersMap
////    private void initData() {
////        try {
////            buyers = buyersReader.read();
////            suppliers = suppliersReader.read();
////        } catch (IOException e) {
////            buyers = new BuyersMap();
////            suppliers = new SuppliersMap();
////        }
////    }
//
////    // MODIFIES: this
////    // EFFECTS: initialize JsonReader & JsonWriter,
////    //          read previously saved data, or initialize new BuyersMap and SuppliersMap
////    private void initPersistence() {
////        buyersReader = new BuyersJsonReader(BUYERS_JSON_STORE);
////        suppliersReader = new SuppliersJsonReader(SUPPLIERS_JSON_STORE);
////        buyersWriter = new JsonWriter(BUYERS_JSON_STORE);
////        suppliersWriter = new JsonWriter(SUPPLIERS_JSON_STORE);
////    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
////        if (e.getSource() == loadItem) {
////            loadData();
////        } else if (e.getSource() == saveItem) {
////            saveData();
////        } else
//        if (e.getSource() == buyersButton) {
//            CardLayout layout = (CardLayout) container.getLayout();
//            layout.show(container, "buyersPanel");
//        } else if (e.getSource() == suppliersButton) {
//            System.out.println("suppliers");
//        } else if (e.getSource() == calculatorButton) {
//            System.out.println("calculator");
//        }
//    }
//
////    // MODIFIES: this
////    // EFFECTS: load BuyersMap and SuppliersMap data into application
////    private void loadData() {
////        try {
////            buyers = buyersReader.read();
////        } catch (IOException e) {
////            System.out.println("Unable to read from file: " + BUYERS_JSON_STORE);
////        }
////        try {
////            suppliers = suppliersReader.read();
////        } catch (IOException e) {
////            System.out.println("Unable to read from file: " + SUPPLIERS_JSON_STORE);
////        }
////    }
////
////    // MODIFIES: this
////    // EFFECTS: save BuyersMap and SuppliersMap data into file
////    private void saveData() {
////        try {
////            buyersWriter.open();
////            buyersWriter.write(buyers);
////            buyersWriter.close();
////        } catch (FileNotFoundException e) {
////            System.out.println("Unable to write to file: " + BUYERS_JSON_STORE);
////        }
////        try {
////            suppliersWriter.open();
////            suppliersWriter.write(suppliers);
////            suppliersWriter.close();
////        } catch (FileNotFoundException e) {
////            System.out.println("Unable to write to file: " + SUPPLIERS_JSON_STORE);
////        }
////    }
//
//}
