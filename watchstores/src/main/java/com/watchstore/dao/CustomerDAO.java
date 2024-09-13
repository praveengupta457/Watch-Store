package com.watchstore.dao;


import org.hibernate.Session;

import com.watchstore.entity.Customers;
import com.watchstore.utilities.HibernateUtil;

public class CustomerDAO extends GenericDAO<Customers, Integer> {

    public CustomerDAO() {
        super(Customers.class);
    }
    
    //  method to retrieve customer based on email id will help in authentication during login 

    
    public Customers findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customers customer = null;
        try {
            customer = session.createQuery("FROM Customers WHERE emailId = :email", Customers.class)
                              .setParameter("email", email)
                              .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customer;
    }
}
