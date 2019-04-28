package dao;

import ejb.dto.User;
import ejb.exceptions.InvalidLoginCredentialsException;

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

public class UserDAO {
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static User getUser(String login, String password) throws InvalidLoginCredentialsException {
        init();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> hh = query.from(User.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("name"), login));
        predicates.add(cb.equal(hh.get("password"), password));

        query.where(predicates.toArray(new Predicate[] {}));

        List<User> users = new LinkedList<User>();
        try {
            TypedQuery<User> q = em.createQuery(query);
            users = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }

        if (users == null || users.size() != 1)
            throw new InvalidLoginCredentialsException();

        return users.get(0);
    }

    private static void init() {
        if (factory==null)
            factory = Persistence.createEntityManagerFactory("DataSource");
        if (em==null)
            em = factory.createEntityManager();
    }
}
