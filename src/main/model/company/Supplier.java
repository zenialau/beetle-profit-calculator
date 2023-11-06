package src.main.model.company;

import java.util.Objects;

// Represents a supplier with their name and country
public class Supplier extends Trader {
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

}
