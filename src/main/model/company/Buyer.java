package src.main.model.company;

import org.json.JSONObject;
import src.main.persistence.Writable;

import java.util.Objects;

// Represent a buyer with name, instagram account and address
public class Buyer extends Trader implements Writable {
    private String igAccount;
    private String address;

    // Constructs a buyer with their name, Instagram account, and an empty list of purchases
    public Buyer(String name, String igAccount, String address) {
        super(name);
        this.igAccount = igAccount;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return Objects.equals(name, buyer.name) && Objects.equals(igAccount, buyer.igAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, igAccount);
    }

    public String getIgAccount() {
        return igAccount;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("igAccount", igAccount);
        json.put("address", address);
        return json;
    }
}
