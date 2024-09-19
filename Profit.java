import java.util.ArrayList;
import java.util.Scanner;

public class Profit {
    private static double Sst;

    
    public Profit() {
        Sst = 0.0;
    }

    
    public static void setSst(double sst) {
        Sst = sst;
    }

    
    public static double getSst() {
        return Sst;
    }

    
    public void getProfitMenu(ArrayList<Product> product) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("Please Select an Option:");
            System.out.println("1. Sales Report");
            System.out.println("2. Inventory Report");
            System.out.println("3. Profit Report");
            System.out.println("0. Quit");
            System.out.print("Enter a Number: ");
            
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    SalesReport(product);
                    break;
                case 2:
                    InventoryReport(product);
                    break;
                case 3:
                    ProfitReport(product);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Option, Please choose again.");
            }
        } while (option != 0);

        scanner.close();
    }

    
    public void SalesReport(ArrayList<Product> productList) {
        double totalSales = 0.0;

        System.out.println("== Sales Report ==");
        System.out.println("ProdID | ProdName      | Quantity Sold | Sales Price (RM)");
        for (Product product : productList) {
            double salesPrice = product.getProPrice() * product.getQuantitysold
();
            totalSales += salesPrice;
            System.out.printf("%s   | %-13s | %5d         | %10.2f%n", product.setprodId
(), product.getProdName(), product.getQuantitysold
(), salesPrice);
        }
        System.out.printf("Total Sales Amount: RM %.2f%n", totalSales);
    }

    public void InventoryReport(ArrayList<Product> productList) {
        double totalRestockCost = 0.0;

        System.out.println("== Inventory Report ==");
        System.out.println("ProdID | ProdName      | Quantity Restock | Restock Price (RM)");
        for (Product product : productList) {
            double restockCost = product.getProPrice() * product.getQuantitysold
();
            totalRestockCost += restockCost;
            System.out.printf("%s   | %-13s | %5d           | %10.2f%n", product.setprodId
(), product.getProdName(), product.getQuantitysold
(), restockCost);
        }
        System.out.printf("Total Restock Cost: RM %.2f%n", totalRestockCost);
    }


    public void ProfitReport(ArrayList<Product> productList) {
        double totalProfit = 0.0;
        double totalSales = 0.0;
        double totalRestockCost = 0.0;

        System.out.println("== Profit Report ==");
        System.out.println("ProdID | ProdName      | Sales Price (RM) | Restock Price (RM) | Profit (RM)");
        for (Product product : productList) {
            double salesPrice = product.getProPrice() * product.getQuantitysold
();
            double restockCost = product.getProPrice() * product.getQuantitysold
(); 
            double profit = salesPrice - restockCost;

            totalSales += salesPrice;
            totalRestockCost += restockCost;
            totalProfit += profit;

            System.out.printf("%s   | %-13s | %10.2f       | %10.2f        | %10.2f%n", product.setprodId
(), product.getProdName(), salesPrice, restockCost, profit);
        }
        System.out.printf("Total Sales Amount: RM %.2f%n", totalSales);
        System.out.printf("Total Restock Cost: RM %.2f%n", totalRestockCost);
        System.out.printf("Total Profit: RM %.2f%n", totalProfit);
    }
}
