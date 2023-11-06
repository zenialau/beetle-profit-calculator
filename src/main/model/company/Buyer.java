package src.main.model.company;

import java.util.Objects;

// Represent a buyer with name, instagram account and address
public class Buyer extends Trader {
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

}
