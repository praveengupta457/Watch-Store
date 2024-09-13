package com.watchstore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.watchstore.entity.Order;
import com.watchstore.utilities.HibernateUtil;

public class OrderDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // Add a new order
    public void addOrder(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Geting all orders for a customer by customer ID
    @SuppressWarnings("unchecked")
    public List<Order> getOrdersByCustomerId(int customerId) {
        Session session = sessionFactory.openSession();
        List<Order> orders = null;

        try {
            String hql = "FROM Order WHERE customer.customerId = :customerId";
            @SuppressWarnings("deprecation")
			Query<Order> query = session.createQuery(hql);
            query.setParameter("customerId", customerId);
            orders = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return orders;
    }

    // Get an order by its ID
    public Order getOrderById(int orderId) {
        Session session = sessionFactory.openSession();
        Order order = null;

        try {
            order = session.get(Order.class, orderId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return order;
    }

    // Update an order
    @SuppressWarnings("deprecation")
	public void updateOrder(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Delete an order
    @SuppressWarnings("deprecation")
	public void deleteOrder(int orderId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, orderId);
            if (order != null) {
                session.delete(order);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
