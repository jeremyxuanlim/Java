public class Product {
    private String prodId;
    private String prodName;
    private int quantity;
    private double price;

    // Constructor
    public Product(String prodId, String prodName, int quantity, double price) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public String getProdId() {
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Method to get total sales amount for this product
    public double getTotalSales() {
        return quantity * price;
    }
}

