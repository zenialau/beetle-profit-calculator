package src.main.persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.Purchase;

import java.io.IOException;

// Reference: JsonSerializationDemo
// Represents a reader that reads BuyersMap from JSON data stored in file
public class BuyersJsonReader extends JsonReader {

    // EFFECTS: constructs BuyersReader to read from source file
    public BuyersJsonReader(String source) {
        super(source);
    }

    // EFFECTS: reads BuyersMap from file and returns it;
    //          throws IOException if an error occurs reading data from file
    @Override
    public BuyersMap read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMap(jsonObject);
    }

    // EFFECTS: parses BuyersMap from JSONObject and returns it
    @Override
    protected BuyersMap parseMap(JSONObject jsonObject) {
        BuyersMap bm = new BuyersMap();
        addBuyers(bm, jsonObject);
        return bm;
    }

    // MODIFIES: bm
    // EFFECTS: parses Buyer(s) from JSONObject and adds them to BuyersMap
    protected void addBuyers(BuyersMap bm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("buyers");
        for (Object json : jsonArray) {
            JSONObject nextBuyer = (JSONObject) json;
            addBuyer(bm, nextBuyer);
        }
    }

    // MODIFIES: bm
    // EFFECTS: create Buyer and add it to bm
    private void addBuyer(BuyersMap bm, JSONObject jsonBuyer) {
        String name = jsonBuyer.getString("name");
        String igAccount = jsonBuyer.getString("igAccount");
        String address = jsonBuyer.getString("address");
        JSONArray jsonArray = jsonBuyer.getJSONArray("purchaseList");

        Buyer buyer = new Buyer(name, igAccount, address);
        try {
            bm.addBuyer(buyer);
        } catch (DuplicateException e) {
            throw new RuntimeException(e);
        }

        for (Object json : jsonArray) {
            JSONObject nextPurchase = (JSONObject) json;
            Purchase purchase = parsePurchase(nextPurchase);
            bm.addPurchase(buyer, purchase);
        }
    }

}
