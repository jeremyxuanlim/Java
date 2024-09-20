import java.util.Scanner;

public class mainMenu {
    static Scanner input = new Scanner(System.in);
    static Manager manager = new Manager();
    static Staff staff = new Staff();

    public static void main(String[] args) {
        manager.loadAccount();
        staff.loadAccount();
        
        while (true) {
            System.out.println("Welcome to Inventory Management System!\n" +
                               "1. Manager Login\n2. Manager Register\n" +
                               "3. Staff Login\n4. Staff Register\n" +
                               "5. Exit");
            System.out.print("Choose an option > ");
            int n = input.nextInt();
            input.nextLine();

            switch (n) {
                case 1:
                    manager.login();
                    break;
                case 2:
                    manager.register();
                    break;
                case 3:
                    staff.login();
                    break;
                case 4:
                    staff.register();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Error, try again.");
            }
        }
    }
}
