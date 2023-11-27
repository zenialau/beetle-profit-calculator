package src.main.ui;

import src.main.model.ProfitCalculator;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.company.SuppliersMap;
import src.main.model.company.Trader;
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

    private void generalFormatMenu(String menu, Buyer buyer, Purchase purchase) {
        boolean keepGoing = true;
        String command;

        while (keepGoing){
            displayOptions(menu, buyer, purchase);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandOptions(menu, buyer, purchase, command);
            }
        }
    }

    private void displayOptions(String menu, Buyer buyer, Purchase purchase) {
        switch (menu) {
            case "mainMenu":
                displayMainMenu();
                break;
            case "buyersMenu":
                displayBuyersMenu();
                break;
            case "viewBuyerMenu":
                displayViewBuyerMenu(buyer);
                break;
            case "addPurchaseMenu":
                displayAddPurchaseMenu(purchase);
                break;
            case "calculatorMenu":
                displayCalculatorMenu();
                break;
        }
    }

    private void processCommandOptions(String menu, Buyer buyer, Purchase purchase, String command) {
        switch (menu) {
            case "mainMenu":
                processCommand(command);
                break;
            case "buyersMenu":
                processBuyersCommand(command);
                break;
            case "viewBuyerMenu":
                processViewBuyerCommand(command, buyer);
                break;
            case "addPurchaseMenu":
                processAddPurchaseCommand(command, purchase);
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

    private void displayBuyersMenu() {
        for (Trader buyer : buyers.getListOfBuyers()) {
            System.out.println(buyer.getName());
        }
        System.out.println();
        System.out.println("Add buyer > a");
        System.out.println("View buyer details or add purchase > enter buyer's name");
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

    private void viewBuyerMenu(Buyer buyer) {
        generalFormatMenu("viewBuyerMenu", buyer, null);
    }

    private void displayViewBuyerMenu(Buyer buyer) {
        for (Purchase purchase : buyers.getPurchaseList(buyer).getPurchaseList()) {
            for (InventoryItem item : purchase.getItems()) {
                System.out.print(item.getName() + ": " + item.getPrice() + ", ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Add purchase to " + buyer.getName() + "(buyer) > a");
        System.out.println("Quit > q");
    }

    private void processViewBuyerCommand(String command, Buyer buyer) {
        if (command.equals("a")) {
            addPurchaseMenu(buyer);
        }
    }

    private void addPurchaseMenu(Buyer buyer) {
        Purchase purchase = new Purchase();
        generalFormatMenu("addPurchaseMenu", buyer, purchase);
        buyers.getPurchaseList(buyer).addPurchase(purchase);
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

    private void suppliersMenu() {

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
