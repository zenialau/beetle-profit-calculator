package src.main.persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import src.main.model.company.Customer;
import src.main.model.company.CustomersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.Purchase;

import java.io.IOException;

// Reference: JsonSerializationDemo
// Represents a reader that reads CustomersMap from JSON data stored in file
public class CustomersJsonReader extends JsonReader {

    // EFFECTS: constructs CustomersReader to read from source file
    public CustomersJsonReader(String source) {
        super(source);
    }

    // EFFECTS: reads CustomersMap from file and returns it;
    //          throws IOException if an error occurs reading data from file
    @Override
    public CustomersMap read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMap(jsonObject);
    }

    // EFFECTS: parses CustomersMap from JSONObject and returns it
    @Override
    protected CustomersMap parseMap(JSONObject jsonObject) {
        CustomersMap bm = new CustomersMap();
        addCustomers(bm, jsonObject);
        return bm;
    }

    // MODIFIES: bm
    // EFFECTS: parses Buyer(s) from JSONObject and adds them to CustomersMap
    protected void addCustomers(CustomersMap bm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("customers");
        for (Object json : jsonArray) {
            JSONObject nextBuyer = (JSONObject) json;
            addBuyer(bm, nextBuyer);
        }
    }

    // MODIFIES: bm
    // EFFECTS: create Buyer and add it to bm
    private void addBuyer(CustomersMap bm, JSONObject jsonBuyer) {
        String name = jsonBuyer.getString("name");
        String igAccount = jsonBuyer.getString("igAccount");
        String address = jsonBuyer.getString("address");
        JSONArray jsonArray = jsonBuyer.getJSONArray("purchaseList");

        Customer customer = new Customer(name, igAccount, address);
        try {
            bm.addBuyer(customer);
        } catch (DuplicateException e) {
            throw new RuntimeException(e);
        }

        for (Object json : jsonArray) {
            JSONObject nextPurchase = (JSONObject) json;
            Purchase purchase = parsePurchase(nextPurchase);
            bm.addPurchase(customer, purchase);
        }
    }

}
