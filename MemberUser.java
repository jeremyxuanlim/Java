import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MemberUser extends User {
    public MemberUser(String name, String email, String password, double currency) {
        super(name, email, password, currency);
    }

    @Override
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("MemberUser.txt", true)) {
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
            System.out.println("2. Purchase Item");
            System.out.println("0. Logout");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    addCurrencyPrompt();
                    break;
                case 2:
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
        String itemId;
        int quantity;

        while (true) {
            System.out.print("Enter the item ID you want to purchase (or type '0' to cancel): ");
            itemId = scanner.nextLine();

            if (itemId.equals("0")) {
                System.out.println("Purchase canceled.");
                return; // Return to the previous menu
            }

            System.out.print("Enter the quantity: ");
            quantity = scanner.nextInt();

            // Find the item details
            String[] selectedItem = null;
            for (String[] item : items) {
                if (item[0].equals(itemId)) {
                    selectedItem = item;
                    break;
                }
            }

            if (selectedItem == null) {
                System.out.println("Item not found. Please try again.");
                continue; // Ask for item ID again
            }

            int availableQuantity = Integer.parseInt(selectedItem[2]);
            double itemPrice = Double.parseDouble(selectedItem[3]);
            double totalPrice = itemPrice * quantity;

            if (quantity > availableQuantity) {
                System.out.println("Insufficient quantity available. Please try again.");
                continue; // Ask for quantity again
            }

            // Apply 15% discount for members
            double discount = 0.15 * totalPrice;
            double finalPrice = totalPrice - discount;

            System.out.printf("Original Price: $%.2f\n", totalPrice);
            System.out.printf("Discount: $%.2f (15%% off)\n", discount);
            System.out.printf("Final Price: $%.2f\n", finalPrice);

            if (finalPrice > currency) {
                System.out.println("Insufficient currency for this purchase. Please try again.");
                continue; // Ask for currency again
            }

            // Deduct currency and update item quantity
            currency -= finalPrice;
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
            Order order = new Order(email, itemId, selectedItem[1], quantity, finalPrice);
            Order.saveOrderToFile(order);

            System.out.println("Purchase successful! Total cost after discount: $" + finalPrice);
            saveCurrencyToFile(); // Save updated currency to the file
            break; // Exit the loop after successful purchase
        }
    }

    @Override
    public void upgradeToMember() {
        // This method can simply inform that the user is already a member
        System.out.println("You are already a member.");
    }
}
