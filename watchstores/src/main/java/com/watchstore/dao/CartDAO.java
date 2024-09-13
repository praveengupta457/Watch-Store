package com.watchstore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.watchstore.entity.Carts;
import com.watchstore.utilities.HibernateUtil;

import java.util.List;

public class CartDAO {

    // Method to save a cart entry (Add product to cart)
    @SuppressWarnings("deprecation")
	public void saveCart(Carts cart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cart);  // insert item to table
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Method to retrieve cart items for a specific customer
    
    public List<Carts> getCartItemsByCustomerId(int customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
            Query<Carts> query = session.createQuery(
            		"FROM Carts WHERE customer.customerId = :customerId"  
            		,Carts.class); // hql query from the hibernate in mysql database 

            query.setParameter("customerId", customerId);  // setting parameter
            return query.getResultList();  // ResultSet is used to read retrieved data
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to remove an item from the cart
    
    @SuppressWarnings("deprecation")
	public void removeCartItem(Carts cartItem) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
//            Carts cart = session.get(Carts.class, cartItem);
            if (cartItem != null) {
                session.delete(cartItem);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    
}
