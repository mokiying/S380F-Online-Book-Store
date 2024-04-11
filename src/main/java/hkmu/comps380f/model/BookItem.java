package hkmu.comps380f.model;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


public class BookItem {
    @Id
    private long id;

    @ManyToOne
    private Book book;
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
