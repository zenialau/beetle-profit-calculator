package src.main.model.company;

import java.util.Objects;

// Represent a buyer with name and instagram account,
// bought items (+ price) and times bought from Hadrian
public class Buyer {
    private String name;
    private String igAccount;

    // Constructs a buyer with their name, Instagram account, and an empty list of purchases
    public Buyer(String name, String igAccount) {
        this.name = name;
        this.igAccount = igAccount;
    }

    @Override
    public boolean equals(Object obj) {
        Buyer b = (Buyer) obj;
        return name.equals(b.getName()) && igAccount.equals(b.getIgAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, igAccount);
    }

    public String getName() {
        return name;
    }

    public String getIgAccount() {
        return igAccount;
    }

}
