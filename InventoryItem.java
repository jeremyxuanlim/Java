public class InventoryItem {
    private String itemId;
    private String itemName;
    private int stock;
    private double price;

    public InventoryItem(String itemId, String itemName, int stock, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.stock = stock;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void updateStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return itemId + " " + itemName + " Stock: " + stock + " Price: RM" + price;
    }
}
