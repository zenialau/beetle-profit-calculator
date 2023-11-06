package src.main.model;

import src.main.model.company.BuyersMap;
import src.main.model.company.SuppliersMap;

// calculates the profit from BuyersMap and SuppliersMap
public class ProfitCalculator {
    private BuyersMap buyers;
    private SuppliersMap suppliers;
    private double revenue;
    private double expense;

    // EFFECTS: constructs a profit calculator with buyers and suppliers and set revenue/expense
    //          to 0
    public ProfitCalculator(BuyersMap buyers, SuppliersMap suppliers) {
        this.buyers = buyers;
        this.suppliers = suppliers;
        revenue = 0;
        expense = 0;
    }

    // MODIFIES: this
    // EFFECTS: updates the calculator to take new values from buyers and suppliers
    public void update() {
        revenue = buyers.getTotalRevenueOrExpense();
        expense = suppliers.getTotalRevenueOrExpense();
    }

    // EFFECTS: calculate and return the profit using the revenue and expense
    public double getProfit() {
        return revenue - expense;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getExpense() {
        return expense;
    }
}
