package src.main.ui;

import src.main.model.ProfitCalculator;
import src.main.model.company.*;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;

import java.util.Scanner;

public class BeetleConsole {
    private Scanner input;
    private BuyersMap buyers;
    private SuppliersMap suppliers;
    private ProfitCalculator calc;

    public BeetleConsole() {
        runConsole();
    }

    private void runConsole() {
        init();
        mainMenu();
    }

    private void generalFormatMenu(String menu, Trader trader, Purchase purchase) {
        boolean keepGoing = true;
        String command;

        while (keepGoing){
            displayOptions(menu, trader, purchase);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandOptions(menu, trader, purchase, command);
            }
        }
    }

    private void displayOptions(String menu, Trader trader, Purchase purchase) {
        switch (menu) {
            case "mainMenu":
                displayMainMenu();
                break;
            case "buyersMenu":
                displayTradersMenu("buyer");
                break;
            case "viewTraderMenu":
                displayViewTraderMenu(trader);
                break;
            case "addPurchaseMenu":
                displayAddPurchaseMenu(purchase);
                break;
            case "suppliersMenu":
                displayTradersMenu("supplier");
                break;
            case "calculatorMenu":
                displayCalculatorMenu();
                break;
        }
    }

    private void processCommandOptions(String menu, Trader trader, Purchase purchase, String command) {
        switch (menu) {
            case "mainMenu":
                processCommand(command);
                break;
            case "buyersMenu":
                processBuyersCommand(command);
                break;
            case "viewTraderMenu":
                processViewTraderCommand(command, trader);
                break;
            case "addPurchaseMenu":
                processAddPurchaseCommand(command, purchase);
                break;
            case "suppliersMenu":
                processSuppliersCommand(command);
                break;
        }
    }

    private void mainMenu() {
        generalFormatMenu("mainMenu", null, null);
    }

    private void displayMainMenu() {
        System.out.println("Buyers > b");
        System.out.println("Suppliers > s");
        System.out.println("See profit > p");
        System.out.println("Quit > q");
    }

    private void processCommand(String command) {
        switch (command) {
            case "b":
                buyersMenu();
                break;
            case "s":
                suppliersMenu();
                break;
            case "p":
                calculatorMenu();
                break;
        }
    }

    private void buyersMenu() {
        generalFormatMenu("buyersMenu", null, null);
    }

    private void suppliersMenu() {
        generalFormatMenu("suppliersMenu", null, null);
    }

    private void displayTradersMenu(String t) {
        String trader = "";
        if (t.equals("buyer")) {
            for (Buyer buyer : buyers.getListOfBuyers()) {
                System.out.println(buyer.getName());
            }
            trader = "buyer";
        } else if (t.equals("supplier")) {
            for (Supplier supplier : suppliers.getListOfSuppliers()) {
                System.out.println(supplier.getName());
            }
            trader = "supplier";
        }
        System.out.println();
        System.out.println("Add " + trader + " > a");
        System.out.println("View " + trader + " details or add purchase > enter " + trader + "'s name");
        System.out.println("Quit > q");
    }

    // REQUIRES: no buyers have the same name
    private void processBuyersCommand(String command) {
        if (command.equals("a")) {
            addBuyerMenu();
        }
        for (Buyer buyer : buyers.getListOfBuyers()) {
            if (buyer.getName().toLowerCase().equals(command)) {
                viewBuyerMenu(buyer);
            }
        }
    }

    // REQUIRES: no suppliers have the same name
    private void processSuppliersCommand(String command) {
        if (command.equals("a")) {
            addSupplierMenu();
        }
        for (Supplier supplier : suppliers.getListOfSuppliers()) {
            if (supplier.getName().toLowerCase().equals(command)) {
                viewSupplierMenu(supplier);
            }
        }
    }

    private void addBuyerMenu() {
        System.out.println("Name: ");
        String name = input.next();
        System.out.println("IG account: ");
        String igAccount = input.next();
        System.out.println("Address: ");
        String address = input.next();
        Buyer buyer = new Buyer(name, igAccount, address);
        try {
            buyers.addBuyer(buyer);
        } catch (DuplicateException e) {
            System.out.println("Buyer already exist!");
        }
    }

    private void addSupplierMenu() {
        System.out.println("Name: ");
        String name = input.next();
        System.out.println("Country: ");
        String country = input.next();
        Supplier supplier = new Supplier(name, country);
        try {
            suppliers.addSupplier(supplier);
        } catch (DuplicateException e) {
            System.out.println("Supplier already exist!");
        }
    }

    private void viewBuyerMenu(Buyer buyer) {
        generalFormatMenu("viewTraderMenu", buyer, null);
    }

    private void viewSupplierMenu(Supplier supplier) {
        generalFormatMenu("viewTraderMenu", supplier, null);
    }

    private void displayViewTraderMenu(Trader trader) {
        String t = "";
        if (trader instanceof Buyer) {
            printPurchaseList(buyers, trader);
            t = "buyer";
        } else if (trader instanceof Supplier) {
            printPurchaseList(suppliers, trader);
            t = "supplier";
        }
        System.out.println();
        System.out.println("Add purchase to " + trader.getName() + "(" + t + ") > a");
        System.out.println("Quit > q");
    }

    private void printPurchaseList(TradersMap traders, Trader trader) {
        for (Purchase purchase : traders.getPurchaseList(trader).getPurchaseList()) {
            System.out.print(purchase.getPurchaseDate().toString() + "   ");
            for (InventoryItem item : purchase.getItems()) {
                System.out.print(item.getName() + ": " + item.getPrice() + ", ");
            }
            System.out.println();
        }
    }

    private void processViewTraderCommand(String command, Trader trader) {
        if (command.equals("a")) {
            addPurchaseMenu(trader);
        }
    }

    private void addPurchaseMenu(Trader trader) {
        Purchase purchase = new Purchase();
        System.out.println("Enter the date of purchase: (YYYY-MM-DD)");
        String date = input.next();
        purchase.setPurchaseDate(date);
        generalFormatMenu("addPurchaseMenu", trader, purchase);
        if (trader instanceof Buyer) {
            buyers.getPurchaseList((Buyer) trader).addPurchase(purchase);
        } else if (trader instanceof Supplier) {
            suppliers.getPurchaseList((Supplier) trader).addPurchase(purchase);
        }

    }

    private void displayAddPurchaseMenu(Purchase purchase) {
        for (InventoryItem item : purchase.getItems()) {
            System.out.println(item.getName() + ": " + item.getPrice());
        }
        System.out.println("Add item to purchase > a");
        System.out.println("Quit and confirm purchase > q");
    }

    private void processAddPurchaseCommand(String command, Purchase purchase) {
        if (command.equals("a")) {
            addItemMenu(purchase);
        }
    }

    private void addItemMenu(Purchase purchase) {
        System.out.println("Name: ");
        String name = input.next();
        System.out.println("Description: ");
        String description = input.next();
        System.out.println("Size (mm): ");
        double size = Double.parseDouble(input.next());
        System.out.println("Quality: ");
        String quality = input.next();
        System.out.println("Comment: ");
        String comment = input.next();
        System.out.println("Price (CAD): ");
        double price = Double.parseDouble(input.next());
        InventoryItem item = new InventoryItem(name, description, size, quality, comment, price);
        purchase.addItem(item);
    }

    private void calculatorMenu() {
        generalFormatMenu("calculatorMenu", null, null);
    }

    private void displayCalculatorMenu() {
        System.out.println("Profit Calculator");
        calc.update();
        System.out.println("Profit: " + calc.getProfit());
        System.out.println("Reload > r");
        System.out.println("Quit > q");
    }

    private void init() {
        input = new Scanner(System.in);
        buyers = new BuyersMap();
        suppliers = new SuppliersMap();
        calc = new ProfitCalculator(buyers, suppliers);
    }

}
