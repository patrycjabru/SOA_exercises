package com.DAO;

import com.DTO.User;

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
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DataSource");;
    private static EntityManager em = factory.createEntityManager();

    public static User getUser(String name){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> hh = query.from(User.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("name"), name));

        query.where(predicates.toArray(new Predicate[] {}));

        List<User> users = new LinkedList<User>();
        try {
            TypedQuery<User> q = em.createQuery(query);
            users = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }

        return users.get(0);
    }

    public static void addUser(String name, int age, String avatar) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setAvatar(avatar);

        DAO.add(newUser, em);
    }

    public void deleteUser(User user) {
        try {
            User foundUser = em.find(User.class, user.getId());
            DAO.delete(foundUser, em);
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }
    }
}
