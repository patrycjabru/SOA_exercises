package dao;

import ejb.dto.Subscription;
import ejb.dto.Thread;
import ejb.dto.User;

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

public class SubscriptionDAO {
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static void addSubscription(Subscription subscription) {
        DAO.add(subscription);
    }

    public static List<Thread> getThreadsSubscribedByUser(int userId) {
        User user = UserDAO.getUserById(userId);

        //TODO make it work
        init();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Thread> query = cb.createQuery(Thread.class);
        Root<Thread> hh = query.from(Thread.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("user"), user));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Thread> threads = new LinkedList<Thread>();
        try {
            TypedQuery<Thread> q = em.createQuery(query);
            threads = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return threads;
    }

    public static Subscription getSubscription(int userId, int threadId) {
        User user = UserDAO.getUserById(userId);
        Thread thread = ThreadDAO.getThreadById(threadId);

        init();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subscription> query = cb.createQuery(Subscription.class);
        Root<Subscription> hh = query.from(Subscription.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("user"), user));
        predicates.add(cb.equal(hh.get("thread"), thread));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Subscription> subscriptions = new LinkedList<Subscription>();
        try {
            TypedQuery<Subscription> q = em.createQuery(query);
            subscriptions = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        if (subscriptions==null || subscriptions.size()!=1)
            return null;

        return subscriptions.get(0);
    }

    public static void update(Subscription subscription) {
        //TODO implement
    }

    private static void init() {
        if (factory==null)
            factory = Persistence.createEntityManagerFactory("DataSource");
        if (em==null)
            em = factory.createEntityManager();
    }
}
