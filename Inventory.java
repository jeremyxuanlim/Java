/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventory;

/**
 *
 * @author Ng Pui Xian
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }


    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getProductName() + " added to the inventory.");
    }


    public void removeProduct(String productId) {
        Product productToRemove = null;
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            products.remove(productToRemove);
            System.out.println(productToRemove.getProductName() + " removed from inventory.");
        } else {
            System.out.println("Product not found!");
        }
    }


    public void updateStock(String productId, int newQuantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setQuantity(newQuantity);
                System.out.println(product.getProductName() + " stock updated to " + newQuantity);
                return;
            }
        }
        System.out.println("Product not found!");
    }


    public void checkExpiryDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();

        System.out.println("Checking expired medicines...");
        for (Product product : products) {
            if (product instanceof Medicine) {
                Medicine med = (Medicine) product;
                LocalDate expiryDate = LocalDate.parse(med.getExpiryDate(), formatter);

                long daysToExpiry = ChronoUnit.DAYS.between(today, expiryDate);
                if (daysToExpiry <= 30) {
                    System.out.println("ALERT: " + med.getProductName() + " is expiring in " + daysToExpiry + " days (Expires on: " + med.getExpiryDate() + ")");
                }
            }
        }
    }


    public void checkStockLevels() {
        System.out.println("Checking stock levels...");
        for (Product product : products) {
            if (product.getQuantity() < 10) { // Assuming 10 as a threshold
                System.out.println("ALERT: " + product.getProductName() + " stock is low. Current quantity: " + product.getQuantity());
            }
        }
    }


    public Product searchProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        System.out.println("Product with ID: " + productId + " not found.");
        return null;
    }


    public Product searchProductByName(String productName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        System.out.println("Product with name: " + productName + " not found.");
        return null;
    }

    public void sortByPrice() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        System.out.println("Inventory sorted by price.");
    }

    public void sortByName() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getProductName().compareToIgnoreCase(p2.getProductName());
            }
        });
        System.out.println("Inventory sorted by name.");
    }

    public void sellProduct(String productId, int quantitySold) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                if (product.getQuantity() >= quantitySold) {
                    product.setQuantity(product.getQuantity() - quantitySold);
                    double totalCost = quantitySold * product.getPrice();
                    System.out.println("Sale successful: " + quantitySold + " units of " + product.getProductName() +
                                       " sold for a total of $" + totalCost);
                } else {
                    System.out.println("Not enough stock for " + product.getProductName() + ". Current stock: " + product.getQuantity());
                }
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    
    public void displayInventory() {
        System.out.println("\nInventory List:");
        for (Product product : products) {
            System.out.println("ID: " + product.getProductId() + ", Name: " + product.getProductName() +
                    ", Price: $" + product.getPrice() + ", Quantity: " + product.getQuantity());
        }
    }
}
