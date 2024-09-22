import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ViewStaff {
    private static final String FILE_NAME = "staff_accounts.txt";
    private Scanner input = new Scanner(System.in);

    public void execute() {
        HashMap<String, String[]> staffData = loadStaffData();

        if (staffData.isEmpty()) {
            System.out.println("No staff records found.");
            return;
        }

        System.out.printf("%-10s %-20s %-30s %-30s%n", "ID", "Password", "Email", "Name");
        System.out.println("----------------------------------------------------------------------------");

        for (Map.Entry<String, String[]> entry : staffData.entrySet()) {
            String id = entry.getKey();
            String[] details = entry.getValue();
            System.out.printf("%-10s %-20s %-30s %-30s%n", id, details[0], details[1], details[2]);
        }

        System.out.println("Press Enter to return to the menu.");
        input.nextLine();
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
}

