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

    public static User getUserById(int id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> hh = query.from(User.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("id"), id));

        query.where(predicates.toArray(new Predicate[] {}));

        List<User> users = new LinkedList<User>();
        try {
            TypedQuery<User> q = em.createQuery(query);
            users = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        if (users==null || users.size()!=1)
            return null;
        return users.get(0);
    }

    public static void addUser(User user) {
        DAO.add(user, em);
    }

    public static void deleteUser(int id) {
        try {
            User foundUser = em.find(User.class, id);
            DAO.delete(foundUser, em);
        }
        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public static void updateUser(User user) throws Exception {
        try {
            User foundUser = em.find(User.class, user.getId());

            em.getTransaction().begin();
            foundUser.setName(user.getName());
            foundUser.setAge(user.getAge());
            foundUser.setAvatar(user.getAvatar());
            em.getTransaction().commit();
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
            throw e;
        }
    }

    public static void updateUserAge(int userId, int age) throws Exception{
        try {
            User foundUser = em.find(User.class, userId);

            em.getTransaction().begin();
            foundUser.setAge(age);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
            throw e;
        }
    }
}
