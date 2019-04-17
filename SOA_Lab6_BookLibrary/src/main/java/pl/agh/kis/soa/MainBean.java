package pl.agh.kis.soa;

import org.hibernate.Criteria;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name= "mainBean")
@RequestScoped
public class MainBean {
    EntityManagerFactory factory;
    EntityManager em;

    private Author authorFilter;
    private Reader readerFilter;
    private Book bookFilter;
    private int authorId;
    private Borrowing borrowingFilter;
    private int readerId;
    private int bookId;

    public MainBean() {
        factory = Persistence.createEntityManagerFactory("BookLibrary");
        em = factory.createEntityManager();

        authorFilter = new Author();
        readerFilter = new Reader();
        bookFilter = new Book();
        borrowingFilter = new Borrowing();
    }

    public List<Reader> getReaders() {
        List<Reader> readers = new LinkedList<Reader>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Reader> query = cb.createQuery(Reader.class);
        Root<Reader> hh = query.from(Reader.class);
        List<Predicate> predicates = new LinkedList<Predicate>();
        if (readerFilter.getId()!=0)
            predicates.add(cb.equal(hh.get("id"), readerFilter.getId()));
        if (readerFilter.getName()!=null && !readerFilter.getName().equals(""))
            predicates.add(cb.equal(hh.get("name"), readerFilter.getName()));
        if (readerFilter.getSurname()!=null && !readerFilter.getSurname().equals(""))
            predicates.add(cb.equal(hh.get("surname"), readerFilter.getSurname()));
        query.where(predicates.toArray(new Predicate[] {}));
        try {
            TypedQuery<Reader> q = em.createQuery(query);
            readers = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return readers;
    }

    public List<Author> getAuthors() {
        List<Author> authors = new LinkedList<Author>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Author> query = cb.createQuery(Author.class);
        Root<Author> hh = query.from(Author.class);
        List<Predicate> predicates = new LinkedList<Predicate>();
        if (authorFilter.getId()!=0)
            predicates.add(cb.equal(hh.get("id"), authorFilter.getId()));
        if (authorFilter.getName()!=null && !authorFilter.getName().equals(""))
            predicates.add(cb.equal(hh.get("name"), authorFilter.getName()));
        if (authorFilter.getSurname()!=null && !authorFilter.getSurname().equals(""))
            predicates.add(cb.equal(hh.get("surname"), authorFilter.getSurname()));
        query.where(predicates.toArray(new Predicate[] {}));
        try {
            TypedQuery<Author> q = em.createQuery(query);
            authors = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return authors;
    }

    public List<Book> getBooks() {
        List<Book> books = new LinkedList<Book>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> hh = query.from(Book.class);
        List<Predicate> predicates = new LinkedList<Predicate>();
        if (bookFilter.getId()!=0)
            predicates.add(cb.equal(hh.get("id"), bookFilter.getId()));
        if (bookFilter.getTitle()!=null && !bookFilter.getTitle().equals(""))
            predicates.add(cb.equal(hh.get("title"), bookFilter.getTitle()));
        if (authorId!=0) {
            Author author = findAuthorById(authorId);
            predicates.add(cb.equal(hh.get("author"), author));
        }
        query.where(predicates.toArray(new Predicate[] {}));
        try {
            TypedQuery<Book> q = em.createQuery(query);
            books = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return books;
    }

    public List<Borrowing> getBorrowings() {
        List<Borrowing> borrowings = new LinkedList<Borrowing>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Borrowing> query = cb.createQuery(Borrowing.class);
        Root<Borrowing> hh = query.from(Borrowing.class);
        List<Predicate> predicates = new LinkedList<Predicate>();
        if (borrowingFilter.getId()!=0)
            predicates.add(cb.equal(hh.get("id"), borrowingFilter.getId()));
        if (readerId!=0) {
            Reader reader = findReaderById(readerId);
            predicates.add(cb.equal(hh.get("reader"), reader));
        }
        if (bookId!=0) {
            Book book = findBookById(bookId);
            predicates.add(cb.equal(hh.get("book"), book));
        }
        if (borrowingFilter.getBorrow_date() != null) {
            predicates.add(cb.equal(hh.get("borrow_date").as(Date.class), borrowingFilter.getBorrow_date()));
        }
        if (borrowingFilter.getReturn_date() != null) {
            predicates.add(cb.equal(hh.get("return_date"), borrowingFilter.getReturn_date()));
        }

        query.where(predicates.toArray(new Predicate[] {}));
        try {
            TypedQuery<Borrowing> q = em.createQuery(query);
            borrowings = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return borrowings;
    }

    public void deleteReader(Reader reader) {
        try {
            Reader foundReader = em.find(Reader.class, reader.getId());

            em.getTransaction().begin();
            em.remove(foundReader);
            em.getTransaction().commit();
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public void deleteAuthor(Author author) {
        try {
            Author foundAuthor = em.find(Author.class, author.getId());

            em.getTransaction().begin();
            em.remove(foundAuthor);
            em.getTransaction().commit();
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public void deleteBook(Book book) {
        try {
            Book foundBook = em.find(Book.class, book.getId());

            em.getTransaction().begin();
            em.remove(foundBook);
            em.getTransaction().commit();
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public void deleteBorrowing(Borrowing borrowing) {
        try {
            Borrowing foundBorrowing = em.find(Borrowing.class, borrowing.getId());

            em.getTransaction().begin();
            em.remove(foundBorrowing);
            em.getTransaction().commit();
        }

        catch (Exception e) {
            System.err.println("Error when trying to delete data from database: " + e);
            em.getTransaction().rollback();
        }
    }

    public String addReader(String name, String surname) {
        Reader newReader = new Reader();
        newReader.setName(name);
        newReader.setSurname(surname);
        add(newReader);
        return "index";
    }

    public String addAuthor(String name, String surname) {
        Author newAuthor = new Author();
        newAuthor.setName(name);
        newAuthor.setSurname(surname);
        add(newAuthor);
        return "index";
    }

    public String addBook(String title, String authorName) {
        Author author = findAuthor(authorName);

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        add(newBook);
        return "index";
    }

    public String addBorrowing(String bookName, String readerName, Date borrowingDate, Date returnDate) {
        Book book = findBook(bookName);
        Reader reader = findReader(readerName);

        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setReader(reader);
        borrowing.setBorrow_date(borrowingDate);
        borrowing.setReturn_date(returnDate);
        add(borrowing);
        return "index";
    }

    private void add(Object newObject) {
        try {
            em.getTransaction().begin();
            em.persist(newObject);
            em.getTransaction().commit();
            System.out.println("Added to database" + newObject);
        }
        catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to add data to database: " + e);
        }
    }

    private Author findAuthor(String authorName) {
        List<Author> authors = getAuthors();
        for (Author a:authors) {
            if (a.toString().equals(authorName))
                return a;
        }
        return null;
    }

    private Author findAuthorById(int id) {
        List<Author> authors = getAuthors();
        for (Author a:authors) {
            if (a.getId()==id)
                return a;
        }
        return null;
    }

    private Book findBook(String bookName) {
        List<Book> books = getBooks();
        for (Book b:books) {
            if (b.toString().equals(bookName))
                return b;
        }
        return null;
    }

    private Book findBookById(int id) {
        List<Book> books = getBooks();
        for (Book b:books) {
            if (b.getId()==id)
                return b;
        }
        return null;
    }

    private Reader findReader(String readerName) {
        List<Reader> reader = getReaders();
        for (Reader r:reader) {
            if (r.toString().equals(readerName))
                return r;
        }
        return null;
    }

    private Reader findReaderById(int id) {
        List<Reader> reader = getReaders();
        for (Reader r:reader) {
            if (r.getId()==id)
                return r;
        }
        return null;
    }

    public String updateBook(int id, String title, String authorName) {
        try {
            Book foundBook = em.find(Book.class, id);

            em.getTransaction().begin();
            foundBook.setTitle(title);
            Author author = findAuthor(authorName);
            foundBook.setAuthor(author);
            em.getTransaction().commit();

            return "index";
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
            return "";
        }
    }

    public String updateAuthor(int id, String name, String surname) {
        try {
            Author foundAuthor = em.find(Author.class, id);

            em.getTransaction().begin();
            foundAuthor.setName(name);
            foundAuthor.setSurname(surname);
            em.getTransaction().commit();

            return "index";
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
            return "";
        }
    }

    public String updateReader(int id, String name, String surname) {
        try {
            Reader foundReader = em.find(Reader.class, id);

            em.getTransaction().begin();
            foundReader.setName(name);
            foundReader.setSurname(surname);
            em.getTransaction().commit();

            return "index";
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
            return "";
        }
    }

    public String updateBorrowing(int id, String bookName, String readerName, Date borrowDate, Date returnDate) {
        try {
            Borrowing foundBorrowing = em.find(Borrowing.class, id);
            Book book = findBook(bookName);
            Reader reader = findReader(readerName);

            em.getTransaction().begin();
            foundBorrowing.setBook(book);
            foundBorrowing.setReader(reader);
            foundBorrowing.setBorrow_date(borrowDate);
            foundBorrowing.setReturn_date(returnDate);
            em.getTransaction().commit();

            return "index";
        }  catch(Exception e) {
            em.getTransaction().rollback();
            System.err.println("Error when trying to update data in database: " + e);
            return "";
        }
    }

    public Author getAuthorFilter() {
        return authorFilter;
    }

    public void setAuthorFilter(Author authorFilter) {
        this.authorFilter = authorFilter;
    }

    public Reader getReaderFilter() {
        return readerFilter;
    }

    public void setReaderFilter(Reader readerFilter) {
        this.readerFilter = readerFilter;
    }

    public Book getBookFilter() {
        return bookFilter;
    }

    public void setBookFilter(Book bookFilter) {
        this.bookFilter = bookFilter;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Borrowing getBorrowingFilter() {
        return borrowingFilter;
    }

    public void setBorrowingFilter(Borrowing borrowingFilter) {
        this.borrowingFilter = borrowingFilter;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
