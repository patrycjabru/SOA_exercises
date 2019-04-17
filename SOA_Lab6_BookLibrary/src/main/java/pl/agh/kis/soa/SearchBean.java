package pl.agh.kis.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name= "searchBean")
@RequestScoped
public class SearchBean {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("BookLibrary");
    EntityManager em = factory.createEntityManager();

    public List<Book> getTopBooks() {
        List<Book> books = new LinkedList<Book>();
        String sql = "select Book.title,count(Book.title) " +
                "from Borrowing right " +
                "join Book on Borrowing.book = Book " +
                "group by Book.title " +
                "order by count(Book.title) desc";

        try {
            Query query = em.createQuery(sql);
            query.setMaxResults(3);
            books = query.getResultList();
        } catch (Exception e) {
            System.err.println("Error when trying to retrieve data from database: " + e);
        }
        return books;
    }
}
