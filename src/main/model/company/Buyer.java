package src.main.model.company;

import java.util.ArrayList;
import java.util.List;

// Represent a buyer with name and instagram account,
// bought items (+ price) and times bought from Hadrian
public class Buyer {
    private String name;
    private String igAccount;
    private List<Purchase> listOfPurchases;

    // Constructs a buyer with their name, Instagram account, and an empty list of purchases
    public Buyer(String name, String igAccount) {
        this.name = name;
        this.igAccount = igAccount;
        this.listOfPurchases = new ArrayList<>();
    }

//    // MODIFIES: this
//    // EFFECTS: add a purchase to the listOfPurchases
//    public void addPurchase(Purchase purchase) {
//        listOfPurchases.add(purchase);
//    }
//
//    // EFFECTS: return the number of times of purchases made (1 purchase could contain several items)
//    public int getNumPurchases() {
//        return listOfPurchases.size();
//    }
//
//    // EFFECTS: return the total amount (in dollars) of purchases made
//    public double getTotalAmountPurchased() {
//        double amount = 0;
//        for (Purchase purchase: listOfPurchases) {
//            amount += purchase.getCheckoutAmount();
//        }
//        return amount;
//    }

    public String getName() {
        return name;
    }

    public String getIgAccount() {
        return igAccount;
    }

//    public List<Purchase> getListOfPurchases() {
//        return listOfPurchases;
//    }


}
