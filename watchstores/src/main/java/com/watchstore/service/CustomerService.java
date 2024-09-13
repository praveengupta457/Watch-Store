package com.watchstore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.watchstore.dao.CartDAO;
import com.watchstore.dao.OrderDAO;
import com.watchstore.dao.ProductDAO;
import com.watchstore.entity.Carts;
import com.watchstore.entity.Customers;
import com.watchstore.entity.Order;
import com.watchstore.entity.Products;
import com.watchstore.utilities.HibernateUtil;

public class CustomerService {
    private Scanner scanner;
    private ProductDAO productDAO = new ProductDAO();
    private CartDAO cartDAO = new CartDAO();
    private OrderDAO orderDAO = new OrderDAO(); 

    // View all products
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

    // Product selection and add to cart
    public void productSelection(Customers loggedInCustomer) {
        scanner = new Scanner(System.in);

        // Prompt the user to enter the product ID
        System.out.println("Enter the Product ID (int) to select the product: ");
        int productId = scanner.nextInt();

        // Fetch the product from the database using the product ID
        Products selectedProduct = productDAO.findById(productId);

        if (selectedProduct == null) {
            System.out.println("Invalid Product ID, product not found.");
            return;
        }

        // Display product details and ask if they want to add to cart
        System.out.println("Product selected: " + selectedProduct.getProductName() 
                            + " | Price: " + selectedProduct.getPrice());
        System.out.println("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.println("Do you want to add this product to the cart? (yes/no)");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("yes")) {
            addToCart(selectedProduct, loggedInCustomer, quantity);
        } else {
            System.out.println("Product not added to the cart.");
        }
    }

    // Add the selected product to the cart for the logged-in customer
    public void addToCart(Products product, Customers customer, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Create a new cart entry
            Carts cart = new Carts();
            cart.setCustomer(customer);
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cart.setPrice(product.getPrice());
            cart.setTotalPrice(product.getPrice() * quantity); // Calculate total price

            // Persist the cart to the database
            session.persist(cart);
            transaction.commit();

            System.out.println("Product added to the cart successfully!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // View items in the cart
    public void viewCart(Customers loggedInCustomer) {
        List<Carts> cartItems = cartDAO.getCartItemsByCustomerId(loggedInCustomer.getCustomerId());
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("Items in your cart:");
        double totalPrice = 0.0;

        for (Carts cartItem : cartItems) {
            Products product = cartItem.getProduct();
            System.out.println("Product ID: " + product.getProductId()
                               + " | Name: " + product.getProductName()
                               + " | Price: " + product.getPrice()
                               + " | Quantity: " + cartItem.getQuantity()
                               + " | Total Price: " + cartItem.getTotalPrice());
            totalPrice += cartItem.getTotalPrice(); // Sum up total price
        }

        System.out.println("Total Cart Price: " + totalPrice);
    }
    
    
    
    public void removeProductFromCart(Customers loggedInCustomer) {
    	List<Carts> cartItem = cartDAO.getCartItemsByCustomerId(loggedInCustomer.getCustomerId());
    	if(cartItem.isEmpty()) {
    		System.out.println("Your Cart is empty");
    		return;
    		
    	}
    	System.out.println("Items of your cart are: ");
    	System.out.println("============================");
    	
       for(Carts cartItem1 : cartItem) {
    	   Products product=cartItem1.getProduct();
    	   System.out.println("Product ID: "+product.getProductId()
    	   +" | Name: "+product.getProductName()
    	   +" | Price: "+product.getPrice()
    	   + " | Quantity: " + cartItem1.getQuantity()
           + " | Total Price: " + cartItem1.getTotalPrice());
    	   
       }
       
       System.out.println("Enter Product ID to remove item from cart: ");
       Scanner scanner= new Scanner(System.in);
       int removeProduct=scanner.nextInt();
       
       Carts cartItemremove =null;
       for(Carts cartItem1: cartItem) {
    	   if(cartItem1.getProduct().getProductId()==removeProduct) {
    		   cartItemremove = cartItem1;
    		   break;
    		   
    	   }
       }
       if(cartItemremove !=null) {
    	   cartDAO.removeCartItem(cartItemremove);
    	   System.out.println("Item removed from cart..");
       }else {
    	   System.out.println("product not found in your cart");
       }
       
    }

    // Order product for the logged-in customer
    public void orderProduct(Customers loggedInCustomer) {
        scanner = new Scanner(System.in);

        // user has to enter the product ID
        System.out.println("Enter the Product ID (int) to order the product: ");
        int productId = scanner.nextInt();

        // Fetch the product from the database using the product ID
        Products selectedProduct = productDAO.findById(productId);

        if (selectedProduct == null) {
            System.out.println("Invalid Product ID, product not found.");
            return;
        }

        // Display product details and ask if they want to order
        System.out.println("Product selected: " + selectedProduct.getProductName() 
                            + " | Price: " + selectedProduct.getPrice());
        System.out.println("Enter quantity: ");
        int quantity = scanner.nextInt();

        System.out.println("Do you want to order this product (yes/no)");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("yes")) {
            placeOrder(selectedProduct, loggedInCustomer, quantity);
        } else {
            System.out.println("Product not ordered.");
        }
    }

    // Place an order for the selected product
    public void placeOrder(Products product, Customers customer, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Create a new order entry
            Order order = new Order();
            order.setCustomer(customer);
            order.setOrderDate(LocalDateTime.now());
            order.setProduct(product);
            order.setQuantity(quantity);
            order.setPrice(product.getPrice());
            order.setTotalPrice(product.getPrice() * quantity); // Calculate total price

            // Persist the order to the database
            session.persist(order);
            transaction.commit();

            System.out.println("Product ordered successfully!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // View orders for the logged-in customer
    public void viewOrders(Customers loggedInCustomer) {
        List<Order> orders = orderDAO.getOrdersByCustomerId(loggedInCustomer.getCustomerId());
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("Your Orders:");
        for (Order order : orders) {
            Products product = order.getProduct();
            System.out.println("Order ID: " + order.getOrderId()
                               + " | Product ID: " + product.getProductId()
                               + " | Name: " + product.getProductName()
                               + " | Price: " + product.getPrice()
                               + " | Quantity: " + order.getQuantity()
                               + " | Total Price: " + order.getTotalPrice()
                               + " | Date: " + order.getOrderDate());
        }
    }
}
