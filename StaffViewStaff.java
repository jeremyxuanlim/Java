import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class StaffViewStaff {
    private static final String FILE_NAME = "staff_accounts.txt";

    public void displayDetails(String staffId) {
        HashMap<String, String[]> staffData = loadStaffData();

        if (!staffData.containsKey(staffId)) {
            System.out.println("Staff ID not found.");
            return; 
        }

        String[] details = staffData.get(staffId);
        System.out.printf("%-10s %-20s %-30s %-30s%n", "ID", "Password", "Email", "Name");
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-30s %-30s%n", staffId, details[0], details[1], details[2]);

        System.out.println("Press Enter to return to the menu.");
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

