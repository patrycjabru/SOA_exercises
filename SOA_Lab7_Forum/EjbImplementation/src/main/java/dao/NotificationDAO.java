package dao;

import ejb.dto.Notification;
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

public class NotificationDAO {
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static List<Notification> getNotifications(User user) {
        init();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Notification> query = cb.createQuery(Notification.class);
        Root<Notification> hh = query.from(Notification.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("user"), user));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Notification> notifications = new LinkedList<Notification>();
        try {
            TypedQuery<Notification> q = em.createQuery(query);
            notifications = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }

        return notifications;
    }

    private static void init() {
        if (factory==null)
            factory = Persistence.createEntityManagerFactory("DataSource");
        if (em==null)
            em = factory.createEntityManager();
    }
}
