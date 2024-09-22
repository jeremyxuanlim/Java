import java.io.*;
import java.util.*;

public class Inventory {
    private List<InventoryItem> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    // Load items from Item.txt
    public void loadItemsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String itemId = parts[0];
                    String itemName = parts[1];
                    int stock = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);

                    InventoryItem item = new InventoryItem(itemId, itemName, stock, price);
                    items.add(item);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading items: " + e.getMessage());
        }
    }

    // Save updated items to Item.txt
    public void saveItemsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (InventoryItem item : items) {
                writer.write(item.getItemId() + "," + item.getItemName() + "," + item.getStock() + "," + item.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving items: " + e.getMessage());
        }
    }

    // Find item by ID
    public InventoryItem findItemById(String itemId) {
        for (InventoryItem item : items) {
            if (item.getItemId().equals(itemId)) {
                return item;
            }
        }
        return null;  // Item not found
    }

    // Display available items
    public void displayItems() {
        System.out.println("== Available Items ==");
        for (InventoryItem item : items) {
            System.out.println(item);
        }
    }

    // Handle item purchase by reducing stock
    public boolean purchaseItem(String itemId, int quantity) {
        InventoryItem item = findItemById(itemId);
        if (item != null && item.getStock() >= quantity) {
            item.updateStock(item.getStock() - quantity);
            return true;
        } else {
            System.out.println("Insufficient stock or item not found.");
            return false;
        }
    }
}


