package dao;

import ejb.dto.Post;
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

public class PostDAO {
    private static EntityManagerFactory factory;
    private static EntityManager em;

    public static List<Post> getPosts(Thread thread) {
        init();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Post> query = cb.createQuery(Post.class);
        Root<Post> hh = query.from(Post.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("thread_id"), thread.getId()));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Post> posts = new LinkedList<Post>();
        try {
            TypedQuery<Post> q = em.createQuery(query);
            posts = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }

        return posts;
    }

    private static void init() {
        if (factory==null)
            factory = Persistence.createEntityManagerFactory("DataSource");
        if (em==null)
            em = factory.createEntityManager();
    }

    public static Post addPost(Post post) {
        return (Post)DAO.add(post);
    }
}
