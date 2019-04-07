package pl.agh.kis.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name="bean")
@SessionScoped
public class BookBean implements Serializable{
    private List<Book> books;
    private String book;
    private String msg;
    private List<String> excludedCategories;
    private List<String> allCategories;
    private boolean priceInPLN = false;
    private int numberOfSelectedBooks;
    private double totalSelectedBooksPrice;

    public BookBean() {
        loadData();
        Book t = books.get(0);
        setBook(t.getTitle());
        setMsg(t.getTitle() + " selected.");
        prepareAllCategories();
        excludedCategories = new LinkedList<>();
        numberOfSelectedBooks = 0;
        totalSelectedBooksPrice = 0;
    }

    private void loadData() {
        books = new ArrayList<>();
        Book b = new Book("item 1 title", "item 1 author", "romans",10.0,"PLN");
        books.add(b);
        b = new Book("item 2 title", "item 2 author", "comedy",11.34,"PLN");
        books.add(b);
        b = new Book("item 3 title", "item 3 author", "drama", 3.50, "EUR");
        books.add(b);
    }

    private void prepareAllCategories() {
        allCategories = new LinkedList<>();
        for (Book b:books) {
            String category = b.getCategory();
            if (allCategories.contains(category))
                continue;
            allCategories.add(category);
        }
    }

    public void priceDisplayChanged(ValueChangeEvent e){
        for (Book b:books) {
            b.setPrizeInPLN((boolean)e.getNewValue());
        }
    }

    public void categoriesDisplayChanged(ValueChangeEvent e){

    }

    public List<Book> getBooks() {
        List<Book> returnedBooks = new LinkedList<>();
        for (Book b:books) {
            String bookCategory = b.getCategory();
            if (excludedCategories.contains(bookCategory))
                continue;
            returnedBooks.add(b);
        }
        return returnedBooks;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isPriceInPLN() {
        return priceInPLN;
    }

    public void setPriceInPLN(boolean priceInPLN) {
        this.priceInPLN = priceInPLN;
    }

    public List<String> getExcludedCategories() {
        return excludedCategories;
    }

    public void setExcludedCategories(List<String> excludedCategories) {
        this.excludedCategories = excludedCategories;
    }

    public List<String> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<String> allCategories) {
        this.allCategories = allCategories;
    }

    public int getNumberOfSelectedBooks() {
        numberOfSelectedBooks = 0;
        for (Book b:books) {
            if (b.getIsSelected())
                numberOfSelectedBooks++;
        }
        return numberOfSelectedBooks;
    }

    public void setNumberOfSelectedBooks(int numberOfSelectedBooks) {
        this.numberOfSelectedBooks = numberOfSelectedBooks;
    }

    public double getTotalSelectedBooksPrice() {
        totalSelectedBooksPrice = 0;
        for (Book b:books) {
            if (b.getIsSelected())
                totalSelectedBooksPrice += b.getPriceInPLN();
        }
        return totalSelectedBooksPrice;
    }

    public void setTotalSelectedBooksPrice(double totalSelectedBooksPrice) {
        this.totalSelectedBooksPrice = totalSelectedBooksPrice;
    }



}




