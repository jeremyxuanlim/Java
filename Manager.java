import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Manager {
    private static final String FILE_NAME = "manager_accounts.txt";
    private HashMap<String, String> account = new HashMap<>();
    private HashMap<String, String> names = new HashMap<>();
    private Scanner input = new Scanner(System.in);

    public void loadAccount() {
        try (BufferedReader load = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = load.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    account.put(parts[0], parts[1]);
                    names.put(parts[0], parts[3]);
                }
            }
        } catch (FileNotFoundException error) {
            System.out.println("No existing account file found. A new one will be created.");
        } catch (IOException error) {
        System.out.println("Error reading file: " + error.getMessage());
        }
    }

    public void saveToFile(String id, String email, String password, String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(id + "," + password + "," + email + "," + name);
            writer.newLine(); }
        catch (IOException error) {
        System.out.println("Error writing to file: " + error.getMessage());
        }
    }

    public void login() {
        System.out.print("Enter manager ID > ");
        String id = input.nextLine();
    
        System.out.print("Enter password > ");
        String password = input.nextLine();
    
        if (account.containsKey(id) && account.get(id).equals(password)) {
            System.out.println("Manager login successful. Welcome, " + names.get(id) + "!");
            new ManagerMenu().showMenu(); } 
        else {
            System.out.println("Invalid ID or password. Please try again.");
        }
    }
    

    public void register() {
        System.out.println("Register a new manager account");

        System.out.print("Enter email > ");
        String email = input.nextLine();

        System.out.print("Enter ID > ");
        String id = input.nextLine();

        if (account.containsKey(id)) {
            System.out.println("ID already exists. Please try a different one.");
            return;
        }

        System.out.print("Enter password > ");
        String password = input.nextLine();

        System.out.print("Enter your name > ");
        String name = input.nextLine();

        account.put(id, password);
        names.put(id, name);
        saveToFile(id, email, password, name);

        System.out.println("Manager registration successful. You can now log in.");
    }
}
