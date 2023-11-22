package src.main.ui;

import src.main.model.ProfitCalculator;
import src.main.model.company.Buyer;
import src.main.model.company.BuyersMap;
import src.main.model.company.SuppliersMap;
import src.main.model.company.Trader;
import src.main.model.exception.DuplicateBuyerException;
import src.main.model.inventory.PurchaseList;

import java.util.Map;
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
        Map<Trader, PurchaseList> map = buyers.getTradersAndPurchases();
        for (Trader buyer : map.keySet()) {
            System.out.println(buyer.getName());
        }
        System.out.println();
        System.out.println("Add buyer > a");
        System.out.println("Add purchase > enter buyer's name");
        System.out.println("Quit > q");
    }

    // REQUIRES: no buyers have the same name
    private void processBuyersCommand(String command) {
        if (command.equals("a")) {
            addBuyerMenu();
        }
        for (Trader buyer : buyers.getTradersAndPurchases().keySet()) {
            if (buyer.getName().toLowerCase().equals(command)) {
                addBuyerPurchaseMenu(buyer);
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
            buyers.addTrader(buyer);
        } catch (DuplicateBuyerException e) {
            System.out.println("Buyer already exist!");
        }
    }

    private void addBuyerPurchaseMenu(Trader buyer) { // Trader type?
        //stub
        //!!!
        // how specific does he want it???
        // - do you have to already bought the product from suppliers
        //   or
        // - can add whatever you want
        //   if this is the case, how???
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
