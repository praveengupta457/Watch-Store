package com.watchstore;

import java.util.Scanner;

import com.watchstore.entity.Admin;
import com.watchstore.entity.Customers;
import com.watchstore.service.AdminService;
import com.watchstore.service.AuthenticationService;
import com.watchstore.service.CustomerService;

public class AllOperation {

    private AuthenticationService authService = new AuthenticationService();
    private AdminService adminService = new AdminService();
    private CustomerService customerService = new CustomerService();

    public void register(Scanner scanner) {
        System.out.println("\n--- Registration ---");
        System.out.println("1. Register as Admin");
        System.out.println("2. Register as Customer");
        System.out.print("Select an option: ");

        int choice = getIntInput(scanner);

        switch (choice) {
            case 1:
                registerAdmin(scanner);
                break;
            case 2:
                registerCustomer(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void registerAdmin(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        authService.registerAdmin(name, phone, email, password);
    }

    private void registerCustomer(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        authService.registerCustomer(name, phone, email, address, password);
    }

    public void login(Scanner scanner) {
        System.out.println("\n--- Login ---");
        System.out.println("1. Admin Login");
        System.out.println("2. Customer Login");
        System.out.print("Select an option: ");

        int choice = getIntInput(scanner);

        switch (choice) {
            case 1:
                adminLogin(scanner);
                break;
            case 2:
                customerLogin(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void adminLogin(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Admin admin = authService.loginAdmin(email, password);
        if (admin != null) {
            adminMenu(scanner);
        }
    }

    private void customerLogin(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Customers customer = authService.loginCustomer(email, password);
        if (customer != null) {
            customerMenu(scanner, customer);
        }
    }

    private void adminMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. View All Customers");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    adminService.viewAllProducts();
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    adminService.viewAllCustomers();
                    break;
                case 6:
                    back = true;
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        int price = getIntInput(scanner);

        System.out.print("Enter product code: ");
        int code = getIntInput(scanner);

        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product category: ");
        String category = scanner.nextLine();

        adminService.addProduct(name, price, code, description, category);
    }

    private void updateProduct(Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        int productId = getIntInput(scanner);

        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new price: ");
        int price = getIntInput(scanner);

        System.out.print("Enter new product code: ");
        int code = getIntInput(scanner);

        System.out.print("Enter new product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter new product category: ");
        String category = scanner.nextLine();

        adminService.updateProduct(productId, name, price, code, description, category);
    }

    private void deleteProduct(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        int productId = getIntInput(scanner);
        adminService.deleteProduct(productId);
    }

    private void customerMenu(Scanner scanner, Customers customer) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View All Products");
            System.out.println("2. Add product to cart");
            System.out.println("3. View your cart");
            System.out.println("4. Remove Item from Cart");
            System.out.println("5. Place Order");
            System.out.println("6. View your order");
            System.out.println("7. Log Out");
            System.out.print("Select an option: ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    customerService.viewAllProducts();
                    break;
                case 2:
                    customerService.productSelection(customer);
                    break;
                case 3:
                    customerService.viewCart(customer);
                    break;
                case 4:
                    customerService.removeProductFromCart(customer);
                    break;
                case 5:
                    customerService.orderProduct(customer);
                    break;
                case 6:
                    customerService.viewOrders(customer);
                    break;
                case 7:
                    back = true;
                    System.out.println("Logging out.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Utility method to safely get integer input
    int getIntInput(Scanner scanner) {
        int input = -1;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return input;
    }
}
