package dao;

import clojure.lang.Obj;
import ejb.dto.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO {
    public static Object add(Object obj) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("DataSource");;
        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.flush();
            em.getTransaction().commit();
            System.out.println("Added to database" + obj);
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
        }
        return obj;
    }
}
