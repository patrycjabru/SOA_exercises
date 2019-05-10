package com.DAO;

import javax.persistence.EntityManager;

public class DAO {
    public static void add (Object obj, EntityManager em) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            System.out.println("Added to database" + obj);
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
        }
    }

    public static void delete (Object obj, EntityManager em) {
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
    }
}
