package hkmu.comps380f.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Cart {
    private long id;

    //private User user;
    private String username;
    //private List<BookItem> bookItems = new ArrayList<>();
    private Map<Long, BookItem> bookItems = new ConcurrentHashMap<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

//    public List<BookItem> getBookItems() {
//        return bookItems;
//    }
//
//    public void setBookItems(List<BookItem> bookItems) {
//        this.bookItems = bookItems;
//    }

    public Map<Long, BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(Map<Long, BookItem> bookItems) {
        this.bookItems = bookItems;
    }
    public BookItem getBook(long id) {
        return this.bookItems.get(id);
    }

    public Collection<BookItem> getBooks() {
        return this.bookItems.values();
    }

    public void addAttachment(BookItem bookItems) {
        this.bookItems.put(bookItems.getId(), bookItems);
    }

    public int getNumberOfBookItems() {
        return this.bookItems.size();
    }
}
