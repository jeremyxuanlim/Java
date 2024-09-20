import java.util.Scanner;
import java.util.ArrayList;



public class Main {
    static Scanner input;
    static Database database;
    static User loggedInUser;

    public static void main(String[] args) {
        database = new Database();
        int num;
        do {
            System.out.println("Welcome to Inventory Management System!\n"
                    + "0. Exit\n"
                    + "1. Login\n"
                    + "2. Register");
            input = new Scanner(System.in);
            num = input.nextInt();
            switch (num) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Error! Please use a valid number.");
            }
        } while (num != 0);
    }

    private static void login() {
        System.out.println("Enter email: ");
        input.nextLine();
        String email = input.nextLine();
        System.out.println("Enter password: ");
        String password = input.nextLine();
        loggedInUser = database.login(email, password);
        if (loggedInUser != null) {
            userInterface();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void register() {
        System.out.println("Enter name: ");
        input.nextLine();
        String name = input.nextLine();
        System.out.println("Enter email: ");
        String email = input.nextLine();
        System.out.println("Enter password: ");
        String password = input.nextLine();

        User newUser = new NormalUser(name, email, password);
        database.addUser(newUser);
        System.out.println("Registration successful! You can now log in.");
    }

    private static void userInterface() {
        int choice;
        do {
            System.out.println("Name: " + loggedInUser.getName() + "\tCurrency: $" + loggedInUser.getCurrency());
            System.out.println("====================================");
            System.out.println("The operation");
            System.out.println("====================================");
            System.out.println("1. Add your currency");
            System.out.println("2. View items");
            System.out.println("3. View previous orders");
            System.out.println("0. Logout");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    addCurrency();
                    break;
                case 2:
                    viewItems();
                    break;
                case 3:
                    viewPreviousOrders();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    private static void addCurrency() {
        System.out.println("Enter your 16-digit debit card number: ");
        input.nextLine();
        String cardNumber = input.nextLine();
        if (cardNumber.length() == 16) {
            System.out.println("Enter amount to add: ");
            double amount = input.nextDouble();
            loggedInUser.addCurrency(amount);
            System.out.println("Successfully added! New balance: $" + loggedInUser.getCurrency());
            if (loggedInUser instanceof NormalUser) {
                ((NormalUser) loggedInUser).saveToFile();
            } else if (loggedInUser instanceof MemberUser) {
                ((MemberUser) loggedInUser).saveToFile();
            }
        } else {
            System.out.println("Invalid card number.");
        }
    }

    private static void viewItems() {
        System.out.println("ID\tItem\t\tPrice");
        System.out.println("===========================");

        // Assume itemList contains item data from the file
        database.loadItems();

        ArrayList<String[]> itemList = database.getItems();
        for (String[] item : itemList) {
            System.out.printf("%-8s %-16s $%-8s%n", item[0], item[1], item[2]);
        }

        System.out.println("Enter the item ID of the product you wish to buy: ");
        input.nextLine();
        String itemId = input.nextLine();

        // Find the selected item by ID
        String[] selectedItem = null;
        for (String[] item : itemList) {
            if (item[0].equals(itemId)) {
                selectedItem = item;
                break;
            }
        }

        if (selectedItem == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.println("Enter the amount you wish to buy: ");
        int amount = input.nextInt();

        double itemPrice = Double.parseDouble(selectedItem[2]);
        double totalCost = itemPrice * amount;

        System.out.println("You are buying " + amount + " units of " + selectedItem[1] + " for a total cost of $" + totalCost);

        if (loggedInUser.getCurrency() < totalCost) {
            System.out.println("Insufficient currency to complete the purchase.");
            return;
        }

        // Deduct total cost from user's currency
        loggedInUser.addCurrency(-totalCost);
        System.out.println("Purchase successful! Your remaining balance is $" + loggedInUser.getCurrency());

        // Save the order to file
        Order newOrder = new Order(loggedInUser.getEmail(), selectedItem[0], selectedItem[1], amount, totalCost);
        Order.saveOrderToFile(newOrder);

        if (loggedInUser instanceof NormalUser) {
            ((NormalUser) loggedInUser).saveToFile();
        } else if (loggedInUser instanceof MemberUser) {
            ((MemberUser) loggedInUser).saveToFile();
        }
    }

    private static void viewPreviousOrders() {
        System.out.println("Your Previous Orders:");
        System.out.println("ID\tItem\t\tQuantity\tTotal Price\tDate");
        System.out.println("==========================================================");

        database.viewOrdersForUser(loggedInUser.getEmail());
    }
}
