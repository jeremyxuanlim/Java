import java.util.List;

public class Profit {
    private double sst = 0.1; // Example Sales and Service Tax (SST) percentage for profit calculation

    // Method to calculate and display the profit report
    public void generateProfitReport(List<Product> products) {
        System.out.println("== Profit Report ==");
        double totalProfit = 0.0;
        for (Product product : products) {
            double salesAmount = product.getTotalSales();
            double profit = salesAmount * sst; // Example calculation with SST
            totalProfit += profit;
            System.out.println(product.getProdId() + " " + product.getProdName() + " Profit: RM" + profit);
        }
        System.out.println("Total Profit: RM" + totalProfit);
    }
}

