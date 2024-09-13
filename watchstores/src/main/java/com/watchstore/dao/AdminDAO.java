package com.watchstore.dao;
import org.hibernate.Session;

import com.watchstore.entity.Admin;
import com.watchstore.utilities.HibernateUtil;

public class AdminDAO extends GenericDAO<Admin, Integer> {

    public AdminDAO() {
        super(Admin.class);
    }

    //  method to retrieve admin based on email id will help in authentication during login 
    public Admin findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Admin admin = null;
        try {
            admin = session.createQuery("FROM Admin WHERE emailId = :email", Admin.class)
                          .setParameter("email", email)
                          .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return admin;
    }
}
