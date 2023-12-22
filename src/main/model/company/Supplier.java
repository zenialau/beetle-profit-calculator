package src.main.model.company;

import org.json.JSONObject;
import src.main.persistence.Writable;

import java.util.Objects;

// Represents a supplier with their name and country
public class Supplier extends Trader implements Writable {
    private String country;

    public Supplier(String name, String country) {
        super(name);
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(name, supplier.name) && Objects.equals(country, supplier.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }

    public String getCountry() {
        return country;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("country", country);
        return json;
    }
}
