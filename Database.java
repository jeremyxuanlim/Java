import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users = new ArrayList<>();

    public User login(String email, String password) {
        // Check NormalUser.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("NormalUser.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length == 4 && userDetails[1].equals(email) && userDetails[2].equals(password)) {
                    return new NormalUser(userDetails[0], userDetails[1], userDetails[2], Double.parseDouble(userDetails[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check MemberUser.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("MemberUser.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length == 4 && userDetails[1].equals(email) && userDetails[2].equals(password)) {
                    return new MemberUser(userDetails[0], userDetails[1], userDetails[2], Double.parseDouble(userDetails[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Login failed
    }

    public void addUser(NormalUser user) {
        user.saveToFile();
    }

    public void removeUserFromFile(String fileName, String email) throws IOException {
        List<String> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (!userDetails[1].equals(email)) {
                    users.add(line); // Add users that don't match the email
                }
            }
        }

        // Write remaining users back to the file
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String user : users) {
                writer.write(user + "\n");
            }
        }
    }

    public void copyUserToMember(String email, String name, String password, double currency) throws IOException {
        // Write the user details to the MemberUser.txt
        try (FileWriter writer = new FileWriter("MemberUser.txt", true)) {
            writer.write(name + "," + email + "," + password + "," + currency + "\n");
        }
    }

    public List<String[]> getItems() {
        List<String[]> items = new ArrayList<>();
        // Logic to read items from Item.txt and return them
        try (BufferedReader reader = new BufferedReader(new FileReader("Item.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] itemDetails = line.split(",");
                items.add(itemDetails);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

}
