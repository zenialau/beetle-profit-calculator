package src.main.persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import src.main.model.company.Supplier;
import src.main.model.company.SuppliersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.Purchase;

import java.io.IOException;

// Reference: JsonSerializationDemo
// Represents a reader that reads SuppliersMap from JSON data stored in file
public class SuppliersJsonReader extends JsonReader {

    // EFFECTS: constructs SuppliersReader to read from source file
    public SuppliersJsonReader(String source) {
        super(source);
    }

    // EFFECTS: reads SuppliersMap from file and returns it;
    //          throws IOException if an error occurs reading data from file
    @Override
    public SuppliersMap read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMap(jsonObject);
    }

    // EFFECTS: parses SuppliersMap from JSONObject and returns it
    @Override
    protected SuppliersMap parseMap(JSONObject jsonObject) {
        SuppliersMap sm = new SuppliersMap();
        addSuppliers(sm, jsonObject);
        return sm;
    }

    // MODIFIES: sm
    // EFFECTS: parses Suppliers(s) from JSONObject and adds them to SuppliersMap
    protected void addSuppliers(SuppliersMap sm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("suppliers");
        for (Object json : jsonArray) {
            JSONObject nextSupplier = (JSONObject) json;
            addSupplier(sm, nextSupplier);
        }
    }

    // MODIFIES: sm
    // EFFECTS: create Supplier and add it to sm
    private void addSupplier(SuppliersMap sm, JSONObject jsonSupplier) {
        String name = jsonSupplier.getString("name");
        String country = jsonSupplier.getString("country");
        JSONArray jsonArray = jsonSupplier.getJSONArray("purchaseList");

        Supplier supplier = new Supplier(name, country);
        try {
            sm.addSupplier(supplier);
        } catch (DuplicateException e) {
            throw new RuntimeException(e);
        }

        for (Object json : jsonArray) {
            JSONObject nextPurchase = (JSONObject) json;
            Purchase purchase = parsePurchase(nextPurchase);
            sm.addPurchase(supplier, purchase);
        }
    }

}
