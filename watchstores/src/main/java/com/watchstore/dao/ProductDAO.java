package com.watchstore.dao;

import org.hibernate.Session;

import com.watchstore.entity.Products;
import com.watchstore.utilities.HibernateUtil;

public class ProductDAO extends GenericDAO<Products, Integer> {

    public ProductDAO() {
        super(Products.class);
    }

    //  methods specific to Product
    public Products findByProductCode(int code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Products product = null;
        try {
            product = session.createQuery("FROM Products WHERE productCode = :code", Products.class)
                             .setParameter("code", code)
                             .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }
}
