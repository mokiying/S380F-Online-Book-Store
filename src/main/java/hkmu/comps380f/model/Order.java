package hkmu.comps380f.model;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


import java.time.LocalDateTime;
import java.util.*;

public class Order {

    @Id
    private Long id;
    private String userId;
    private LocalDateTime dateTime;
    private String state;
    private int totalNum;
    private Double totalPrice;
    @OneToMany(mappedBy = "order")
    private List<BookItem> bookItems = new ArrayList<>();
    //{ [bookId, name, author, price, qty] }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }
}
