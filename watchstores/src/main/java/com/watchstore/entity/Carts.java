package com.watchstore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Carts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price; // Price of the product at the time of adding to cart

    @Column(nullable = false)
    private double totalPrice; // Calculated as quantity * price

    // Default Constructor
    public Carts() {
    }

    // Parameterized Constructor for easy initialization
    public Carts(Customers customer, Products product, int quantity) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice(); // Fetch product price when adding to cart
        this.totalPrice = this.price * this.quantity; // Calculate total price
    }

    // Getters and Setters

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
        this.price = product.getPrice(); // Update the price when product changes
        this.totalPrice = this.price * this.quantity; // Recalculate total price
    }

    public int getQuantity() {
        return quantity;
    }

    // Set quantity and update total price at the same time
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.price * this.quantity; // Recalculate total price
    }

    public double getPrice() {
        return price;
    }

    // Price is generally set from product, no need for explicit setter
    public void setPrice(double price) {
        this.price = price;
        this.totalPrice = this.price * this.quantity; // Recalculate total price
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // No need to explicitly set total price, but if required, this setter is here
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // To string method for easy display of cart details
    @Override
    public String toString() {
        return "Cart ID: " + cartId 
               + ", Product: " + product.getProductName()
               + ", Quantity: " + quantity 
               + ", Price: " + price 
               + ", Total Price: " + totalPrice;
    }
}
