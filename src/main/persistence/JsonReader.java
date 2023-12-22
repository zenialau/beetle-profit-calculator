package src.main.persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import src.main.model.company.TradersMap;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Reference: JsonSerializationDemo
// Represents a reader that reads map from JSON data stored in file
public abstract class JsonReader {
    protected String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TradersMap from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public abstract TradersMap read() throws IOException;

    // EFFECTS: reads source file as String and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses BuyersMap from JSONObject and returns it
    protected abstract TradersMap parseMap(JSONObject jsonObject);

    // EFFECTS: parses Purchase(s) from JSON object and returns it
    protected Purchase parsePurchase(JSONObject jsonPurchase) {
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
