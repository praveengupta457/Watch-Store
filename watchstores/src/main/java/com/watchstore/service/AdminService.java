package com.watchstore.service;

import java.util.List;


import com.watchstore.dao.ProductDAO;
import com.watchstore.dao.CustomerDAO;
import com.watchstore.entity.Customers;
import com.watchstore.entity.Products;

public class AdminService {

    private ProductDAO productDAO = new ProductDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    // Create a new product
    public void addProduct(String name, int price, int code, String description, String category) {
        Products product = new Products(name, price, code, description, category);
        productDAO.save(product);
        System.out.println("Product added successfully!");
    }

    // Read all products
    public void viewAllProducts() {
        List<Products> products = productDAO.findAll();
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (Products p : products) {
            System.out.println(p.getProductId() + " | " + p.getProductName() + " | " + p.getPrice()
                               + " | " + p.getProductCode() + " | " + p.getProductDescription()
                               + " | " + p.getProductCategory());
        }
    }

    // Update a product
    public void updateProduct(int productId, String name, int price, int code, String description, String category) {
        Products product = productDAO.findById(productId);
        if (product != null) {
            product.setProductName(name);
            product.setPrice(price);
            product.setProductCode(code);
            product.setProductDescription(description);
            product.setProductCategory(category);
            productDAO.update(product);
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete a product
    
    public void deleteProduct(int productId) {
        Products product = productDAO.findById(productId);
        if (product != null) {
            productDAO.delete(product);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    // View all customers
    public void viewAllCustomers() {
        List<Customers> customers = customerDAO.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (Customers	 c : customers) {
            System.out.println(c.getCustomerId() + " | " + c.getName() + " | " + c.getEmailId()
                               + " | " + c.getPhone() + " | " + c.getAddress() + " | " + c.getDate());
        }
    }
}
