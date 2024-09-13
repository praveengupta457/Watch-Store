package com.watchstore;

import java.util.Scanner;
import com.watchstore.utilities.HibernateUtil;

public class App {

    public static void main(String[] args) {
        AllOperation operation = new AllOperation();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Watch Store Application ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = operation.getIntInput(scanner);

            switch (choice) {
                case 1:
                    operation.register(scanner);
                    break;
                case 2:
                    operation.login(scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
        HibernateUtil.shutdown();
    }
}
