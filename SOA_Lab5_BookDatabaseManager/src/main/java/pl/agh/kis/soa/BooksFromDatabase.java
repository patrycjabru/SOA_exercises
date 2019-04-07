package pl.agh.kis.soa;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name= "booksBean")
@RequestScoped
public class BooksFromDatabase {
    EntityManagerFactory factory;
    EntityManager em;

    public BooksFromDatabase() {
        factory = Persistence.createEntityManagerFactory("MySqlDS");
        em = factory.createEntityManager();
    }

    public List<Book> getBooks() {
        List<Book> books = new LinkedList<Book>();
        try {
            TypedQuery<Book> q = em.createQuery("SELECT b FROM Book b", Book.class);
            books = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return books;
    }

    public void deleteBook(Book book) {
        try {
            Book foundBook = em.find(Book.class, book.getIsbn());

            em.getTransaction().begin();
            em.remove(foundBook);
            em.getTransaction().commit();
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }
    }
    public String addBook(String isbn, String title, String author, int year) {
        Book newBook = new Book();
        newBook.setIsbn(isbn);
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setYear(year);
        try {
            em.getTransaction().begin();
            em.persist(newBook);
            em.getTransaction().commit();
            System.out.println("Added to database" + newBook);
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
            return "";
        }
        return "index";
    }

    public String updateBook(String isbn, String title, String author, int year) {
        try {
            Book foundBook = em.find(Book.class, isbn);

            em.getTransaction().begin();
            foundBook.setTitle(title);
            foundBook.setAuthor(author);
            foundBook.setYear(year);
            em.getTransaction().commit();

            return "index";
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
            return "";
        }
    }
}
