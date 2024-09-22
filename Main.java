import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("__        __    _ _      _       ");
        System.out.println("\\ \\      / /_ _| | | ___| | _____");
        System.out.println(" \\ \\ /\\ / / _` | | |/ _ \\ |/ / _ \\");
        System.out.println("  \\ V  V / (_| | | |  __/   <  __/");
        System.out.println("   \\_/\\_/ \\__,_|_|_|\\___|_|\\_\\___|");
        System.out.println();
        System.out.println("-------------------------------------");
            System.out.println("Welcome to the Inventory System");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");

            int choice = input.nextInt();
            input.nextLine(); // Consume the newline

            if (choice == 1) {
                System.out.print("Enter your email: ");
                String email = input.nextLine();
                System.out.print("Enter your password: ");
                String password = input.nextLine();

                User user = database.login(email, password);
                if (user != null) {
                    System.out.println("Login successful!");
                    user.displayUserInterface();
                } else {
                    System.out.println("Login failed. Invalid credentials.");
                }
            } else if (choice == 2) {
                System.out.print("Enter your name: ");
                String name = input.nextLine();
                System.out.print("Enter your email: ");
                String email = input.nextLine();
                System.out.print("Enter your password: ");
                String password = input.nextLine();

                System.out.println("Registering as Normal User...");
                NormalUser newUser = new NormalUser(name, email, password);
                database.addUser(newUser);

                System.out.println("Registration successful! You can now log in.");
            } else if (choice == 0) {
                System.out.println("Exiting the system...");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        input.close(); // Close the scanner
    }
}
