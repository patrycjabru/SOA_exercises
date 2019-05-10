package dao;

import ejb.dto.Thread;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class ThreadDAO {
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static List<Thread> getThreads() {
        init();

        List<Thread> threads  = new LinkedList<Thread>();
        try {
            TypedQuery<Thread> q = em.createQuery("SELECT t FROM Thread t", Thread.class);
            threads = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return threads;
    }

    public static Thread getThreadById(int id) {
        init();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Thread> query = cb.createQuery(Thread.class);
        Root<Thread> hh = query.from(Thread.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("id"), id));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Thread> threads = new LinkedList<Thread>();
        try {
            TypedQuery<Thread> q = em.createQuery(query);
            threads = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }

        if (threads == null || threads.size() != 1)
            return null;

        return threads.get(0);
    }

    public static Thread addThread(Thread thread) {
        return (Thread)DAO.add(thread);
    }

    private static void init() {
        factory = Persistence.createEntityManagerFactory("DataSource");
        em = factory.createEntityManager();
    }
}
