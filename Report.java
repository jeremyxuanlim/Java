import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<Product> products = new ArrayList<>();
    private Profit profitCalculator = new Profit(); // Create an instance of Profit class

    // Method to read product data from a file and store it in an ArrayList
    public void readProductData(String filename) {
        // Implement reading product data
    }

    // Method to generate Sales Report
    public void generateSalesReport() {
        // Implement generating sales report
    }

    // Method to generate Inventory Report
    public void generateInventoryReport() {
        // Implement generating inventory report
    }

    // Method to generate Profit Report by linking to Profit.java
    public void generateProfitReport() {
        profitCalculator.generateProfitReport(products); // Delegate to Profit class
    }
}

