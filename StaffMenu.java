import java.util.Scanner;

public class StaffMenu {
    private Scanner input = new Scanner(System.in);

    public void showMenu(String staffId) {
        while (true) {
            System.out.println("Staff Menu\n" +
                               "1. View My Details\n2. Logout");
            System.out.print("Choose an option > ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    viewMyDetails(staffId);
                    break;
                case 2:
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void viewMyDetails(String staffId) {
        StaffViewStaff viewDetails = new StaffViewStaff();
        viewDetails.displayDetails(staffId);
        input.nextLine();
    }
}
