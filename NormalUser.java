import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class NormalUser extends User {

    // Constructor for new users
    public NormalUser(String name, String email, String password) {
        super(name, email, password, 0.0); // New users start with 0 currency
    }

    // Constructor for loading existing users from file
    public NormalUser(String name, String email, String password, double currency) {
        super(name, email, password, currency);
    }

    @Override
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("NormalUser.txt", true)) {
            writer.write(name + "," + email + "," + password + "," + currency + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayUserInterface() {
        int choice;
        Scanner input = new Scanner(System.in);
        do {
            System.out.printf("Name: %s\tCurrency: $%.2f%n", name, currency);
            System.out.println("====================================");
            System.out.println("1. Add currency");
            System.out.println("2. Upgrade to Member");
            System.out.println("3. Purchase Item");
            System.out.println("0. Logout");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    addCurrencyPrompt();
                    break;
                case 2:
                    upgradeToMember();
                    break;
                case 3:
                    purchaseItem();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);
        input.close(); // Close the scanner
    }

    private void purchaseItem() {
        Database database = new Database();
        List<String[]> items = database.getItems();

        // Display available items
        System.out.println("Available Items:");
        System.out.println("ID\tNAME\t\tQUANTITY\tPRICE");
        System.out.println("==========================================");
        for (String[] item : items) {
            System.out.printf("%-10s %-20s %-10s $%-10s%n", item[0], item[1], item[2], item[3]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the item ID you want to purchase: ");
        String itemId = scanner.nextLine();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();

        // Find the item details
        String[] selectedItem = null;
        for (String[] item : items) {
            if (item[0].equals(itemId)) {
                selectedItem = item;
                break;
            }
        }

        if (selectedItem == null) {
            System.out.println("Item not found.");
            return;
        }

        int availableQuantity = Integer.parseInt(selectedItem[2]);
        double itemPrice = Double.parseDouble(selectedItem[3]);
        double totalPrice = itemPrice * quantity;

        if (quantity > availableQuantity) {
            System.out.println("Insufficient quantity available.");
            return;
        }

        if (totalPrice > currency) {
            System.out.println("Insufficient currency for this purchase.");
            return;
        }

        // Deduct currency and update item quantity
        currency -= totalPrice;
        availableQuantity -= quantity;

        // Update the item in Item.txt
        try (FileWriter writer = new FileWriter("Item.txt")) {
            for (String[] item : items) {
                if (item[0].equals(itemId)) {
                    writer.write(itemId + "," + item[1] + "," + availableQuantity + "," + itemPrice + "\n");
                } else {
                    writer.write(String.join(",", item) + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save the order
        Order order = new Order(email, itemId, selectedItem[1], quantity, totalPrice);
        Order.saveOrderToFile(order);

        System.out.println("Purchase successful! Total cost: $" + totalPrice);
        saveCurrencyToFile(); // Save updated currency to the file
    }

    @Override
    public void upgradeToMember() {
        System.out.println("Upgrading user to Member...");
        if (currency < 50) {
            System.out.println("Insufficient funds to upgrade. You need at least $50.");
            return;
        }
        currency -= 50; // Deduct $50 for upgrade
        Database database = new Database();
        try {
            // Copy the user details to MemberUser.txt
            database.copyUserToMember(email, name, password, currency);
            System.out.println("Upgrade successful! You are now a Member.");
        } catch (IOException e) {
            System.out.println("Failed to upgrade to Member.");
            e.printStackTrace();
        }
    }

    // Additional methods for adding currency, viewing previous orders, etc. can be added here
}
