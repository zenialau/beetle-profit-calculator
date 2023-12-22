package src.main.persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Reference: JsonSerializationDemo
// Represents a reader that reads BuyersMap from JSON data stored in file
public class BuyersJsonReader {
    private String source;

    // EFFECTS: constructs BuyersReader to read from source file
    public BuyersJsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads BuyersMap from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public BuyersMap read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBuyersMap(jsonObject);
    }

    // EFFECTS: reads source file as String and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses BuyersMap from JSONObject and returns it
    private BuyersMap parseBuyersMap(JSONObject jsonObject) {
        BuyersMap bm = new BuyersMap();
        addBuyers(bm, jsonObject);
        return bm;
    }

    // MODIFIES: bm
    // EFFECTS: parses Buyer(s) from JSONObject and adds them to BuyersMap
    private void addBuyers(BuyersMap bm, JSONObject jsonObject) {
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

    // EFFECTS: parses Purchase(s) from JSON object and returns it
    private Purchase parsePurchase(JSONObject jsonPurchase) {
       String date = jsonPurchase.getString("purchaseDate");
       JSONArray jsonArray = jsonPurchase.getJSONArray("items");

       Purchase purchase = new Purchase();
       purchase.setPurchaseDate(date);

       for (Object json :jsonArray) {
           JSONObject nextItem =(JSONObject) json;
           InventoryItem item = parseItem(nextItem);
           purchase.addItem(item);
       }
       return purchase;
    }

    // EFFECTS: parses InventoryItem(s) from JSON object and returns it
    private InventoryItem parseItem(JSONObject jsonItem) {
        String name = jsonItem.getString("name");
        String description = jsonItem.getString("description");
        double size = Double.parseDouble(jsonItem.getString("size"));
        String quality = jsonItem.getString("quality");
        String comment = jsonItem.getString("comment");
        double price = Double.parseDouble(jsonItem.getString("price"));

        InventoryItem item = new InventoryItem(name, description, size,
                                                quality, comment, price);
        return item;
    }

}
