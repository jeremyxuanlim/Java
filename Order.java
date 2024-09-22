import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Order {
    private String userEmail;
    private String itemId;
    private String itemName;
    private int quantity;
    private double totalPrice;
    private LocalDateTime orderDate;

    public Order(String userEmail, String itemId, String itemName, int quantity, double totalPrice) {
        this.userEmail = userEmail;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = LocalDateTime.now();
    }

    public String toFileString() {
        return userEmail + "," + itemId + "," + itemName + "," + quantity + "," + totalPrice + "," + orderDate + "\n";
    }

    public static void saveOrderToFile(Order order) {
        try (FileWriter writer = new FileWriter("orders.txt", true)) {
            writer.write(order.toFileString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
