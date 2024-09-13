package com.watchstore.service;

import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

import com.watchstore.dao.AdminDAO;
import com.watchstore.dao.CustomerDAO;
import com.watchstore.entity.Admin;
import com.watchstore.entity.Customers;

public class AuthenticationService {

    private AdminDAO adminDAO = new AdminDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    // Registration for Admin
    
    public void registerAdmin(String name, String phone, String email, String password) {
        String hashedPassword = hashPassword(password);
        Admin admin = new Admin(name, phone, email, hashedPassword, new Date());
        adminDAO.save(admin);
        System.out.println("Admin registered successfully!");
    }

    // Registration for Customer
    
    public void registerCustomer(String name, String phone, String email, String address, String password) {
        String hashedPassword = hashPassword(password);
        Customers customer = new Customers(name, email, phone, address, hashedPassword, new Date());
        customerDAO.save(customer);
        System.out.println("Customer registered successfully!");
    }

    // Login for Admin
    public Admin loginAdmin(String email, String password) {
        Admin admin = adminDAO.findByEmail(email);
        if (admin != null && checkPassword(password, admin.getPassword())) {
            System.out.println("Admin logged in successfully!");
            return admin;
        }
        System.out.println("Invalid admin credentials.");
        return null;
    }

    // Login for Customer
    public Customers loginCustomer(String email, String password) {
        Customers customer = customerDAO.findByEmail(email);
        if (customer != null && checkPassword(password, customer.getPassword())) {
            System.out.println("Customer logged in successfully!");
            return customer;
        }
        System.out.println("Invalid customer credentials.");
        return null;
    }

    // Password hashing
    
    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Password verification
    
    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

