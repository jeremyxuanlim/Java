/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventory;

/**
 *
 * @author Ng Pui Xian
 */
public class MedicalEquipment extends Product {
    private String equipmentType;

    public MedicalEquipment(String productId, String productName, double price, int quantity, String equipmentType) {
        super(productId, productName, price, quantity);
        this.equipmentType = equipmentType;
    }

    public String getEquipmentType() {
        return equipmentType;
    }
}
