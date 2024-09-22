import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public abstract class User {
    protected String name;
    protected String email;
    protected String password;
    protected double currency;

    public User(String name, String email, String password, double currency) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.currency = currency;
    }

    public abstract void saveToFile();

    public abstract void displayUserInterface();

    public abstract void upgradeToMember(); 

    public void addCurrency(double amount) {
        this.currency += amount;
    }

    public void addCurrencyPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 16-digit debit card number: ");
        String cardNumber = scanner.nextLine();
        if (cardNumber.length() != 16 || !cardNumber.matches("\\d{16}")) {
            System.out.println("Invalid card number.");
            return;
        }
        System.out.print("Enter amount to add: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Failed to add currency.");
        } else {
            addCurrency(amount);
            System.out.println("Successfully added $" + amount + " to your account.");
            saveCurrencyToFile(); // Save updated currency to file
        }
    }

    public void saveCurrencyToFile() {
        try {
            Database database = new Database();
            database.removeUserFromFile("NormalUser.txt", email); // Remove old record
            // Save the updated user back to the appropriate file
            saveToFile(); // Save the updated user details
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
