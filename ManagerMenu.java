import java.util.Scanner;

public class ManagerMenu {
    private Scanner input = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("Manager Menu\n" +
                               "1. View Staff\n2. Edit Staff\n3. Delete Staff\n4. Logout");
            System.out.print("Choose an option > ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    new ViewStaff().execute();
                    break;
                case 2:
                    new EditStaff().execute();
                    break;
                case 3:
                    new DeleteStaff().execute();
                    break;
                case 4:
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
