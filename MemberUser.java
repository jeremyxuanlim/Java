import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MemberUser extends User {
    private String level;
    private Inventory inventory;

    public MemberUser(String id, String password, String level) {
        super(id, password);
        this.level = level;
        this.inventory = new Inventory();  // Initialize inventory for member
    }

    // Load inventory for member to view and purchase items
    public void loadInventory(String filename) {
        inventory.loadItemsFromFile(filename);
    }

    // Display available items
    public void viewItems() {
        inventory.displayItems();
    }

    // Purchase an item by providing the itemId and quantity
    public void purchaseItem(String itemId, int quantity) {
        InventoryItem item = inventory.findItemById(itemId);
        if (item != null && inventory.purchaseItem(itemId, quantity)) {
            double totalCost = item.getPrice() * quantity;
            savePurchaseToFile(itemId, item.getItemName(), quantity, totalCost);
            System.out.println("Purchase successful! You bought " + quantity + " " + item.getItemName() + "(s).");
            inventory.saveItemsToFile("Item.txt");  // Update inventory after purchase
        } else {
            System.out.println("Purchase failed. Please check item availability or stock.");
        }
    }

    // Save purchase details to bought.txt
    private void savePurchaseToFile(String itemId, String itemName, int quantity, double totalCost) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bought.txt", true))) {
            writer.write(this.id + "," + itemId + "," + itemName + "," + quantity + "," + totalCost);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to save purchase details to bought.txt: " + e.getMessage());
        }
    }

    @Override
    public void saveToFile() {
        // Write member user details to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("manager_accounts.txt", true))) {
            writer.write(this.id + "," + this.password + "," + this.level);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to write to manager_accounts.txt: " + e.getMessage());
        }
    }
}

