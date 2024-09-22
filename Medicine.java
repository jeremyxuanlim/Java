/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventory;

/**
 *
 * @author Ng Pui Xian
 */
public class Medicine extends Product {
    private String manufacturer;
    private String expiryDate;
    private boolean requiresPrescription;

    public Medicine(String productId, String productName, double price, int quantity, String manufacturer, String expiryDate, boolean requiresPrescription) {
        super(productId, productName, price, quantity);
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
        this.requiresPrescription = requiresPrescription;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public boolean isRequiresPrescription() {
        return requiresPrescription;
    }
}
