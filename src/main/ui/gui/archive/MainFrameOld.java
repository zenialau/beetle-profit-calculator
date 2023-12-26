//package src.main.ui.gui;
//
//import src.main.model.company.BuyersMap;
//import src.main.model.company.SuppliersMap;
//import src.main.persistence.BuyersJsonReader;
//import src.main.persistence.JsonWriter;
//import src.main.persistence.SuppliersJsonReader;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//
//public class MainFrame extends JFrame implements ActionListener {
//
//    private static final String BUYERS_JSON_STORE = "./data/buyers.json";
//    private static final String SUPPLIERS_JSON_STORE = "./data/suppliers.json";
//
//    private BuyersMap buyers;
//    private SuppliersMap suppliers;
//    private BuyersJsonReader buyersReader;
//    private SuppliersJsonReader suppliersReader;
//    private JsonWriter buyersWriter;
//    private JsonWriter suppliersWriter;
//
//    private JMenuBar menuBar;
//    private JMenu fileMenu;
//    private JMenuItem loadItem;
//    private JMenuItem saveItem;
//
//    public MainFrame() {
//        initPersistence();
//        setupFrame();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initialize JsonReader & JsonWriter,
//    //          read previously saved data, or initialize new BuyersMap and SuppliersMap
//    private void initPersistence() {
//        buyersReader = new BuyersJsonReader(BUYERS_JSON_STORE);
//        suppliersReader = new SuppliersJsonReader(SUPPLIERS_JSON_STORE);
//        buyersWriter = new JsonWriter(BUYERS_JSON_STORE);
//        suppliersWriter = new JsonWriter(SUPPLIERS_JSON_STORE);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: set up JFrame window
//    private void setupFrame() {
//        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null); // center of screen
//        addMenuBar();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: add menu bar for save and load to the top of panel
//    private void addMenuBar() {
//        menuBar = new JMenuBar();
//        fileMenu = new JMenu("File");
//
//        loadItem = new JMenuItem("Load");
//        saveItem = new JMenuItem("Save");
//        loadItem.addActionListener(this);
//        saveItem.addActionListener(this);
//        loadItem.setMnemonic(KeyEvent.VK_L);
//        saveItem.setMnemonic(KeyEvent.VK_S);
//        saveItem.setAccelerator(KeyStroke.getKeyStroke("control S"));
//
//        fileMenu.add(loadItem);
//        fileMenu.add(saveItem);
//
//        menuBar.add(fileMenu);
//        this.setJMenuBar(menuBar);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == loadItem) {
//            loadData();
//        } else if (e.getSource() == saveItem) {
//            saveData();
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: load BuyersMap and SuppliersMap data into application
//    private void loadData() {
//        try {
//            buyers = buyersReader.read();
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + BUYERS_JSON_STORE);
//        }
//        try {
//            suppliers = suppliersReader.read();
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + SUPPLIERS_JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: save BuyersMap and SuppliersMap data into file
//    private void saveData() {
//        try {
//            buyersWriter.open();
//            buyersWriter.write(buyers);
//            buyersWriter.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + BUYERS_JSON_STORE);
//        }
//        try {
//            suppliersWriter.open();
//            suppliersWriter.write(suppliers);
//            suppliersWriter.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + SUPPLIERS_JSON_STORE);
//        }
//    }
//
//}
