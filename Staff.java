public class Staff {
    private String staffId;
    private String name;
    private String password;

    // Constructor
    public Staff(String staffId, String name, String password) {
        this.staffId = staffId;
        this.name = name;
        this.password = password;
    }

    // Login method for staff
    public boolean login(String inputId, String inputPassword) {
        return this.staffId.equals(inputId) && this.password.equals(inputPassword);
    }

    // Method to start the staff menu (after successful login)
    public void startStaffMenu(Inventory inventory) {
        StaffMenu staffMenu = new StaffMenu(inventory); // Pass the inventory to staff menu
        staffMenu.displayMenu();
    }
}
