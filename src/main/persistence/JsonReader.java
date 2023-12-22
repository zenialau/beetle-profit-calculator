//package src.main.persistence;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import src.main.model.company.BuyersMap;
//import src.main.model.company.TradersMap;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//// Reference: JsonSerializationDemo
//// Represents a reader that reads map from JSON data stored in file
//public class JsonReader {
//    private String source;
//
//    // EFFECTS: constructs reader to read from source file
//    public JsonReader(String source) {
//        this.source = source;
//    }
//
//    // EFFECTS: reads tradersMap from file and returns it;
//    // throws IOException if an error occurs reading data from file
//    public TradersMap read() throws IOException { // String parameter to determine buyers or suppliers?
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseWorkRoom(jsonObject);
//    }
//
//    // EFFECTS: reads source file as string and returns it
//    private String readFile(String source) throws IOException {
//        StringBuilder contentBuilder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> contentBuilder.append(s));
//        }
//
//        return contentBuilder.toString();
//    }
//
//    // EFFECTS: parses traders map from JSON object and returns it
//    private TradersMap parseWorkRoom(JSONObject jsonObject) {
//        TradersMap tm = new BuyersMap(); // if else for suppliersMap
//        addBuyers(tm, jsonObject);
//        return tm;
//    }
//
//    // MODIFIES: tm
//    // EFFECTS: parses traders from JSON object and adds them to TradersMap
//    private void addBuyers(TradersMap tm, JSONObject jsonObject) { //!!!
//        JSONArray jsonArray = jsonObject.getJSONArray("buyers");
//        for (Object json: jsonArray) {
//            JSONObject nextTrader = (JSONObject) json;
//            addBuyer(tm, nextTrader);
//        }
//    }
//
//    // MODIFIES: tm
//    // EFFECTS: parses trader from JSON object and adds it to TradersMap
//    private void addBuyer(TradersMap tm, JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//    }
//
//    // MODIFIES: tm
//    // EFFECTS: parses purchases from JSON object and adds it to the corresponding PurchaseList of Trader
//    private void addPurchases(TradersMap tm, JSONObject jsonObject) {
//        //stub
//    }
//
//    // MODIFIES: bc
//    // EFFECTS: parses traders from JSON object and adds it to TradersMap
//    private void addBook(TradersMap tm, JSONObject jsonObject) {
//        String title = jsonObject.getString("title");
//        String author = jsonObject.getString("author");
//        String genre = jsonObject.getString("genre");
//        String haveReadString = jsonObject.getString("haveRead");
//        String ratingString = jsonObject.getString("rating");
//
//        Book book = new Book(title, author, genre);
//        if (haveReadString.equals("true")) {
//            book.readBook();
//        }
//        book.rateBook(Double.parseDouble(ratingString));
//
//        tm.addBook(book);
//    }
//}
