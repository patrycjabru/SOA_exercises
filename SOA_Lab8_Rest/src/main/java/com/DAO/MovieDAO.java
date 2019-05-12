package com.DAO;

import com.DTO.Movie;
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

public class MovieDAO {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("DataSource");;
    private static EntityManager em = factory.createEntityManager();

    public static Movie getMovieById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> hh = query.from(Movie.class);
        List<Predicate> predicates = new LinkedList<Predicate>();

        predicates.add(cb.equal(hh.get("id"), id));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Movie> movies = new LinkedList<Movie>();
        try {
            TypedQuery<Movie> q = em.createQuery(query);
            movies = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        if (movies==null || movies.size()!=1)
            return null;
        return movies.get(0);
    }

    public static List<Movie> getAllMovies() {
        List<Movie> movies = new LinkedList<Movie>();
        try {
            TypedQuery<Movie> q = em.createQuery("SELECT m FROM Movie m", Movie.class);
            movies = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return movies;
    }

    public static void addMovie(Movie movie, int userId) {
        User foundUser = em.find(User.class, userId);
        if (foundUser==null)
            throw new NullPointerException();
        movie.setUser(foundUser);
        DAO.add(movie, em);
    }

    public static void deleteMovie(int id) {
        try {
            Movie foundMovie = em.find(Movie.class, id);
            DAO.delete(foundMovie, em);
        }
        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public static void updateMovie(Movie movie) throws Exception{
        try {
            Movie foundMovie = em.find(Movie.class, movie.getId());

            em.getTransaction().begin();
            foundMovie.setTitle(movie.getTitle());
            foundMovie.setUri(movie.getUri());
            foundMovie.setUser(movie.getUser());
            em.getTransaction().commit();
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
            throw e;
        }
    }

    public static List<Movie> getMovieByTitle(String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
        Root<Movie> hh = query.from(Movie.class);
        List<Predicate> predicates = new LinkedList<Predicate>();
        predicates.add(cb.equal(hh.get("title"), title));

        query.where(predicates.toArray(new Predicate[] {}));

        List<Movie> movies = new LinkedList<Movie>();
        try {
            TypedQuery<Movie> q = em.createQuery(query);
            movies = q.getResultList();
        }
        catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }

        return movies;
    }

    public static Object getMovieURIs() {
        List<String> uris = new LinkedList<String>();
        List<Movie> movies = getAllMovies();

        for (Movie m : movies) {
            uris.add(m.getUri());
        }
        return uris;
    }
}
