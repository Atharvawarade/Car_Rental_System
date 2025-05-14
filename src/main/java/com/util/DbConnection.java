package com.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DbConnection {
private static EntityManagerFactory emf;
    
    static {
        try {
            // Create the EntityManagerFactory using persistence unit name
            emf = Persistence.createEntityManagerFactory("postgres");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public static EntityTransaction getTransaction(EntityManager em) {
        return em.getTransaction();
    }
    
    public static void shutdown() {
        // Close the EntityManagerFactory
        if (emf != null) {
            emf.close();
        }
    }
}
