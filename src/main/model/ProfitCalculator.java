package src.main.model;

import src.main.model.company.CustomersMap;
import src.main.model.company.SuppliersMap;

// calculates the profit from CustomersMap and SuppliersMap
public class ProfitCalculator {
    private CustomersMap customers;
    private SuppliersMap suppliers;
    private double revenue;
    private double expense;

    // EFFECTS: constructs a profit calculator with customers and suppliers and set revenue/expense
    //          to 0
    public ProfitCalculator(CustomersMap customers, SuppliersMap suppliers) {
        this.customers = customers;
        this.suppliers = suppliers;
        revenue = 0;
        expense = 0;
    }

    // MODIFIES: this
    // EFFECTS: updates the calculator to take new values from customers and suppliers
    public void update() {
        revenue = customers.getTotalRevenue();
        expense = suppliers.getTotalExpense();
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
