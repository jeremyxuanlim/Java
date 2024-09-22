/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.inventory;

/**
 *
 * @author Ng Pui Xian
 */
public class InventoryMain {
    public static void main(String[] args) {
        Inventory pharmacyInventory = new Inventory();


        Medicine medicine1 = new Medicine("M001", "Paracetamol", 2.50, 100, "PharmaCo", "2025-12-31", false);
        pharmacyInventory.addProduct(medicine1);

        Medicine medicine2 = new Medicine("M002", "Amoxicillin", 12.00, 50, "HealthPharma", "2024-09-30", true);
        pharmacyInventory.addProduct(medicine2);

        MedicalEquipment equipment1 = new MedicalEquipment("E001", "Thermometer", 15.00, 20, "Digital");
        pharmacyInventory.addProduct(equipment1);


        pharmacyInventory.displayInventory();


        pharmacyInventory.sellProduct("M001", 10); 
        pharmacyInventory.sellProduct("M002", 60); 


        pharmacyInventory.checkExpiryDates();
        pharmacyInventory.checkStockLevels();


        pharmacyInventory.displayInventory();
    }
}
