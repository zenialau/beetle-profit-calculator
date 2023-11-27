package src.main.ui;

import src.main.model.ProfitCalculator;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.company.SuppliersMap;
import src.main.model.company.Trader;
import src.main.model.exception.DuplicateException;
import src.main.model.inventory.InventoryItem;
import src.main.model.inventory.Purchase;
import src.main.model.inventory.PurchaseList;

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

    private void mainMenu() {
        boolean keepGoing = true;
        String command;

        while (keepGoing){
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
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
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayBuyersMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processBuyersCommand(command);
            }
        }
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
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayViewBuyerMenu(buyer);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processViewBuyerCommand(command, buyer);
            }
        }
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
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayAddPurchaseMenu(purchase);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            }  else if (command.equals("c")) {
                keepGoing = false;
                buyers.getPurchaseList(buyer).addPurchase(purchase);
            } else {
                processAddPurchaseCommand(command, purchase);
            }
        }
    }

    private void displayAddPurchaseMenu(Purchase purchase) {
        for (InventoryItem item : purchase.getItems()) {
            System.out.println(item.getName() + ": " + item.getPrice());
        }
        System.out.println("Add item to purchase > a");
        System.out.println("Confirm purchase > c");
        System.out.println("Quit > q");
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
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayCalculatorMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            }
        }
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
