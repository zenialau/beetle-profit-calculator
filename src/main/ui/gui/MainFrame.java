package src.main.ui.gui;

import src.main.model.company.CustomersMap;
import src.main.model.company.SuppliersMap;
import src.main.persistence.CustomersJsonReader;
import src.main.persistence.JsonWriter;
import src.main.persistence.SuppliersJsonReader;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// the main window frame of gui
public class MainFrame implements ActionListener, WindowListener {
    private static final int SCREEN_WIDTH = 900;
    private static final int SCREEN_HEIGHT = 600;

    private String customersStore;
    private String suppliersStore;

    private GUISystem system;

    private CustomersJsonReader customersReader;
    private SuppliersJsonReader suppliersReader;
    private JsonWriter customersWriter;
    private JsonWriter suppliersWriter;

    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadItem;
    private JMenuItem saveItem;

    private PanelsContainer panelsContainer;

    // EFFECTS: constructs main frame
    public MainFrame(GUISystem system) {
        this.system = system;
        initPersistence();
        setupFrame();

        this.panelsContainer = new PanelsContainer(this.system);

        mainFrame.add(panelsContainer.getContainer());

        mainFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: set up JFrame window
    private void setupFrame() {
        mainFrame = new JFrame();
        mainFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(this);
        mainFrame.setLocationRelativeTo(null); // center of screen
        addMenuBar();
    }

    // MODIFIES: this
    // EFFECTS: add menu bar for save and load to the top of panel
    private void addMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        loadItem.setMnemonic(KeyEvent.VK_L);
        saveItem.setMnemonic(KeyEvent.VK_S);
        saveItem.setAccelerator(KeyStroke.getKeyStroke("control S"));

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);

        menuBar.add(fileMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: initialize JsonReader & JsonWriter,
    //          read previously saved data, or initialize new CustomersMap and SuppliersMap
    private void initPersistence() {
        customersStore = system.getCustomersJsonStore();
        suppliersStore = system.getSuppliersJsonStore();

        customersReader = system.getCustomersReader();
        suppliersReader = system.getSuppliersReader();
        customersWriter = system.getCustomersWriter();
        suppliersWriter = system.getSuppliersWriter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadItem) {
            loadData();
        } else if (e.getSource() == saveItem) {
            saveData();
        }
    }

    // MODIFIES: this
    // EFFECTS: load CustomersMap and SuppliersMap data into application
    protected void loadData() {
        try {
            CustomersMap customers = customersReader.read();
            system.setCustomersMap(customers);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + customersStore);
        }
        try {
            SuppliersMap suppliers = suppliersReader.read();
            system.setSuppliersMap(suppliers);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + suppliersStore);
        }
    }

    // MODIFIES: this
    // EFFECTS: save CustomersMap and SuppliersMap data into file
    protected void saveData() {
        try {
            customersWriter.open();
            customersWriter.write(system.getCustomersMap());
            customersWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + customersStore);
        }
        try {
            suppliersWriter.open();
            suppliersWriter.write(system.getSuppliersMap());
            suppliersWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + suppliersStore);
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {
        // do nothing
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int result = JOptionPane.showConfirmDialog(null,
                "Do you want to save?", "WARNING!!!", JOptionPane.YES_NO_CANCEL_OPTION);
        switch (result) {
            case 0:
                saveData();
            case 1:
                mainFrame.dispose();
            case 2:
                break;
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // do nothing
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // do nothing
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // do nothing
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // do nothing
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // do nothing
    }
}
