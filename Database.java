import java.io.*;
import java.util.*;

public class Database {
    private ArrayList<User> users;
    private ArrayList<String[]> items;

    public Database() {
        users = new ArrayList<>();
        items = new ArrayList<>();
        loadUsersFromFile("NormalUser.txt");
        loadUsersFromFile("MemberUser.txt");
    }

    public void addUser(User user) {
        users.add(user);
        if (user instanceof NormalUser) {
            user.saveToFile();
        } else if (user instanceof MemberUser) {
            user.saveToFile();
        }
    }

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void loadUsersFromFile(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String[] userData = fileScanner.nextLine().split(",");
                String name = userData[0];
                String email = userData[1];
                String password = userData[2];
                double currency = Double.parseDouble(userData[3]);

                if (filename.equals("NormalUser.txt")) {
                    users.add(new NormalUser(name, email, password, currency));
                } else if (filename.equals("MemberUser.txt")) {
                    users.add(new MemberUser(name, email, password, currency));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User file not found.");
        }
    }

    public void loadItems() {
        items.clear();
        try (Scanner fileScanner = new Scanner(new File("Item.txt"))) {
            while (fileScanner.hasNextLine()) {
                String[] itemData = fileScanner.nextLine().split(",");
                items.add(itemData);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Item file not found.");
        }
    }

    public ArrayList<String[]> getItems() {
        return items;
    }

    public void viewOrdersForUser(String userEmail) {
        try (Scanner fileScanner = new Scanner(new File("orders.txt"))) {
            while (fileScanner.hasNextLine()) {
                String[] orderData = fileScanner.nextLine().split(",");
                if (orderData[0].equals(userEmail)) {
                    System.out.printf("%-8s %-16s %-8s $%-8s %s%n", orderData[1], orderData[2], orderData[3], orderData[4], orderData[5]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous orders found.");
        }
    }
}
