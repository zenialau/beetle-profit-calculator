//package src.main.model.oldinventory;
//
//import java.util.ArrayList;
//import java.util.List;
//
//// a list of purchases (value of BuyersMap)
//public class OldPurchaseList {
//    private List<OldPurchase> purchaseList;
//
//    // EFFECTS: constructs an empty list of purchases
//    public OldPurchaseList() {
//        purchaseList = new ArrayList<>();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: add a purchase to this list
//    public void addPurchase(OldPurchase purchase) {
//        purchaseList.add(purchase);
//    }
//
//    // EFFECTS: return the total amount (in dollars) of purchase in this list
//    public double getAmountPurchased() {
//        double amount = 0;
//        for (OldPurchase purchase: purchaseList) {
//            amount += purchase.getCheckoutAmount();
//        }
//        return amount;
//    }
//
//    public int getNumPurchases() {
//        return purchaseList.size();
//    }
//
//    public List<OldPurchase> getPurchaseList() {
//        return purchaseList;
//    }
//
//    public boolean isEmpty() {
//        return purchaseList.isEmpty();
//    }
//
//    public OldPurchase get(int index) {
//        return purchaseList.get(index);
//    }
//}
