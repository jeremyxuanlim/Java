import java.util.Scanner;

public class ReportMain {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Sample product loading (in a real application, load from file)
        inventory.addItem(new Product("P001", "PC Case", 10, 250.0));
        inventory.addItem(new Product("P002", "Motherboard", 5, 500.0));
        
        Report report = new Report(inventory);
        Scanner scanner = new Scanner(System.in);

        // Main menu loop
        while (true) {
            System.out.println("== Main Menu ==");
            System.out.println("1: Sales Report");
            System.out.println("2: Inventory Report");
            System.out.println("3: Profit Report");
            System.out.println("0: Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    report.generateSalesReport();
                    break;
                case 2:
                    report.generateInventoryReport();
                    break;
                case 3:
                    report.generateProfitReport();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}
