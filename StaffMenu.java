import java.util.Scanner;

public class StaffMenu {
    private Inventory inventory;  // Inventory for the staff to access
    private Report report;        // Report object for report generation

    // Constructor
    public StaffMenu(Inventory inventory) {
        this.inventory = inventory;
        this.report = new Report(inventory); // Initialize with the given inventory
    }

    // Method to display the staff menu and allow actions
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        // Main menu loop
        while (true) {
            System.out.println("== Staff Menu ==");
            System.out.println("1: View Sales Report");
            System.out.println("2: View Inventory Report");
            System.out.println("3: View Profit Report");
            System.out.println("4: View Staff Details");
            System.out.println("0: Logout");
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
                case 4:
                    viewStaffDetails();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    // Method to view staff details (placeholder)
    private void viewStaffDetails() {
        System.out.println("Staff details feature is not yet implemented.");
    }
}
