import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EditStaff {
    private static final String FILE_NAME = "staff_accounts.txt";
    private Scanner input = new Scanner(System.in);

    public void execute() {
        HashMap<String, String[]> staffData = loadStaffData();

        if (staffData.isEmpty()) {
            System.out.println("No staff records found.");
            return;
        }

        System.out.printf("%-10s %-20s %-30s %-30s%n", "ID", "Password", "Email", "Name");
        System.out.println("-------------------------------------------------------------");

        for (Map.Entry<String, String[]> entry : staffData.entrySet()) {
            String id = entry.getKey();
            String[] details = entry.getValue();
            System.out.printf("%-10s %-20s %-30s %-30s%n", id, details[0], details[1], details[2]);
        }

        System.out.print("Enter staff ID to edit > ");
        String id = input.nextLine();

        if (staffData.containsKey(id)) {
            String[] details = staffData.get(id);

            System.out.println("Current details:\n" +
                               "Password: " + details[0] + "\n" +
                               "Email: " + details[1] + "\n" +
                               "Name: " + details[2]);

            System.out.print("Enter new password (leave blank to keep current) > ");
            String newPassword = input.nextLine();
            if (newPassword.isEmpty()) newPassword = details[0];

            System.out.print("Enter new email (leave blank to keep current) > ");
            String newEmail = input.nextLine();
            if (newEmail.isEmpty()) newEmail = details[1];

            System.out.print("Enter new name (leave blank to keep current) > ");
            String newName = input.nextLine();
            if (newName.isEmpty()) newName = details[2];

            saveStaffData(id, newPassword, newEmail, newName);
            System.out.println("Staff details updated.");
        } else {
            System.out.println("No details found for ID: " + id);
        }
    }

    private HashMap<String, String[]> loadStaffData() {
        HashMap<String, String[]> accountDetails = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    accountDetails.put(parts[0], new String[]{parts[1], parts[2], parts[3]});
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return accountDetails;
    }

    private void saveStaffData(String id, String password, String email, String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp_" + FILE_NAME))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equals(id)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            writer.write(id + "," + password + "," + email + "," + name);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }

        // Replace original file with updated file
        new java.io.File(FILE_NAME).delete();
        new java.io.File("temp_" + FILE_NAME).renameTo(new java.io.File(FILE_NAME));
    }
}
