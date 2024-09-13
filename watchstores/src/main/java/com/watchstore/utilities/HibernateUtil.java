package com.watchstore.utilities;
import com.watchstore.entity.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {


         private static SessionFactory sessionFactory;
         
         static {
        	 try {
        		 sessionFactory = new Configuration().configure()
        				.addAnnotatedClass(Admin.class) 
        				.addAnnotatedClass(Customers.class)       				 
        				.addAnnotatedClass(Order.class)       				 
        				.addAnnotatedClass(Carts.class) 
        				.addAnnotatedClass(Products.class)       				 
        				.buildSessionFactory();
        				 
        	 }catch(Throwable exception) {
             System.err.print("intital session factory creation failed: "+exception);
             
             throw new ExceptionInInitializerError(exception);
        		 
        	 }
         }
         
         //getting hibernate session factory
         public static SessionFactory getSessionFactory() {
        	 return sessionFactory;
         }
         //shutdown session factory 
         public static void shutdown() {
        	 
        	 // Close caches and connection pools
        	 	getSessionFactory().close();
         }
	

}
