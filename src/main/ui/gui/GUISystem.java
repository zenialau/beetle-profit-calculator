package src.main.ui.gui;

import src.main.model.company.CustomersMap;
import src.main.model.company.SuppliersMap;
import src.main.persistence.CustomersJsonReader;
import src.main.persistence.JsonWriter;
import src.main.persistence.SuppliersJsonReader;

import java.awt.*;
import java.io.IOException;

// gui system that keep track of customers and suppliers in the background
public class GUISystem {

    public static final Font TITLE_FONT = new Font("Serif", Font.PLAIN, 26);
    public static final Font PLAIN_18_FONT = new Font("Serif", Font.PLAIN, 18);

    private static final String CUSTOMERS_JSON_STORE = "./data/customers.json";
    private static final String SUPPLIERS_JSON_STORE = "./data/suppliers.json";

    private CustomersMap customers;
    private SuppliersMap suppliers;

    private CustomersJsonReader customersReader;
    private SuppliersJsonReader suppliersReader;
    private JsonWriter customersWriter;
    private JsonWriter suppliersWriter;

    private MainFrame mainFrame;

    // EFFECTS: constructs a gui system by initializing back end elements
    public GUISystem() {
        initPersistence();
        initData();
        mainFrame = new MainFrame(this);
    }

    // MODIFIES: this
    // EFFECTS: initialize JsonReader & JsonWriter,
    //          read previously saved data, or initialize new CustomersMap and SuppliersMap
    private void initPersistence() {
        customersReader = new CustomersJsonReader(CUSTOMERS_JSON_STORE);
        suppliersReader = new SuppliersJsonReader(SUPPLIERS_JSON_STORE);
        customersWriter = new JsonWriter(CUSTOMERS_JSON_STORE);
        suppliersWriter = new JsonWriter(SUPPLIERS_JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: read previously saved data, or initialize new CustomersMap and SuppliersMap
    private void initData() {
        try {
            customers = customersReader.read();
            suppliers = suppliersReader.read();
        } catch (IOException e) {
            customers = new CustomersMap();
            suppliers = new SuppliersMap();
        }
    }

    public CustomersMap getCustomersMap() {
        return customers;
    }

    public void setCustomersMap(CustomersMap customers) {
        this.customers = customers;
    }

    public SuppliersMap getSuppliersMap() {
        return suppliers;
    }

    public void setSuppliersMap(SuppliersMap suppliers) {
        this.suppliers = suppliers;
    }

    public String getCustomersJsonStore() {
        return CUSTOMERS_JSON_STORE;
    }

    public String getSuppliersJsonStore() {
        return SUPPLIERS_JSON_STORE;
    }

    public CustomersJsonReader getCustomersReader() {
        return customersReader;
    }

    public SuppliersJsonReader getSuppliersReader() {
        return suppliersReader;
    }

    public JsonWriter getCustomersWriter() {
        return customersWriter;
    }

    public JsonWriter getSuppliersWriter() {
        return suppliersWriter;
    }

}
