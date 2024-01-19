package src.main.ui.gui;

import src.main.model.company.BuyersMap;
import src.main.model.company.SuppliersMap;
import src.main.persistence.BuyersJsonReader;
import src.main.persistence.JsonWriter;
import src.main.persistence.SuppliersJsonReader;

import java.awt.*;
import java.io.IOException;

// gui system that keep track of buyers and suppliers in the background
public class GUISystem {

    public static final Font TITLE_FONT = new Font("Serif", Font.PLAIN, 26);
    public static final Font PLAIN_20_FONT = new Font("Serif", Font.PLAIN, 18);

    private static final String BUYERS_JSON_STORE = "./data/testGUIBuyers.json"; // "./data/buyers.json"
    private static final String SUPPLIERS_JSON_STORE = "./data/testGUISuppliers.json"; //"./data/suppliers.json"

    private BuyersMap buyers;
    private SuppliersMap suppliers;

    private BuyersJsonReader buyersReader;
    private SuppliersJsonReader suppliersReader;
    private JsonWriter buyersWriter;
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
    //          read previously saved data, or initialize new BuyersMap and SuppliersMap
    private void initPersistence() {
        buyersReader = new BuyersJsonReader(BUYERS_JSON_STORE);
        suppliersReader = new SuppliersJsonReader(SUPPLIERS_JSON_STORE);
        buyersWriter = new JsonWriter(BUYERS_JSON_STORE);
        suppliersWriter = new JsonWriter(SUPPLIERS_JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: read previously saved data, or initialize new BuyersMap and SuppliersMap
    private void initData() {
        try {
            buyers = buyersReader.read();
            suppliers = suppliersReader.read();
            System.out.println("read buyers and suppliers map from data");
        } catch (IOException e) {
            buyers = new BuyersMap();
            suppliers = new SuppliersMap();
            System.out.println("constructed new buyers and suppliers map");
        }
    }

    public BuyersMap getBuyersMap() {
        return buyers;
    }

    public void setBuyersMap(BuyersMap buyers) {
        this.buyers = buyers;
    }

    public SuppliersMap getSuppliersMap() {
        return suppliers;
    }

    public void setSuppliersMap(SuppliersMap suppliers) {
        this.suppliers = suppliers;
    }

    public String getBuyersJsonStore() {
        return BUYERS_JSON_STORE;
    }

    public String getSuppliersJsonStore() {
        return SUPPLIERS_JSON_STORE;
    }

    public BuyersJsonReader getBuyersReader() {
        return buyersReader;
    }

    public SuppliersJsonReader getSuppliersReader() {
        return suppliersReader;
    }

    public JsonWriter getBuyersWriter() {
        return buyersWriter;
    }

    public JsonWriter getSuppliersWriter() {
        return suppliersWriter;
    }







}
