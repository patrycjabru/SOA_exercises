package pl.agh.kis.soa;

public class Book {
    private String title;
    private String author;
    private String category;
    private double price;
    private String currency;
    private boolean prizeInPLN = false;
    private boolean isSelected = false;

    public Book(){

    }

    public Book(String title, String author, String category, double price, String currency) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.currency = currency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCurrency() {
        if (prizeInPLN)
            return "PLN";
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPrice() {
        if (prizeInPLN) {
            double newPrice = getPriceInPLN();
            return newPrice;
        }
        return price;
    }

    public double getPriceInPLN() {
        double rate = ExchangeRates.getExchangeRate(currency);
        double newPrice = price*rate;
        return newPrice;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPrizeInPLN() {
        return prizeInPLN;
    }

    public void setPrizeInPLN(boolean prizeInPLN) {
        this.prizeInPLN = prizeInPLN;
    }


    @Override
    public String toString() {
        return title + " " + author;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean selected) {
        isSelected = selected;
    }




}
