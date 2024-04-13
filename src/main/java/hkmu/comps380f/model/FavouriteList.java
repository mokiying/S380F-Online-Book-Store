package hkmu.comps380f.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FavouriteList {
    @Id
    private TicketUser user;
    private List<Book> BookList = new ArrayList<>();
    ;
    private String listName;

    public FavouriteList(TicketUser user, List<Book> BookList, String listName) {
    }

    public TicketUser getUser() {
        return user;
    }

    public void setUser(TicketUser user) {
        this.user = user;
    }

    public List<Book> getBookList() {
        return BookList;
    }

    public void addbook(Book book) {
        this.BookList.add(book);
    }

    public void deleteBook(Book book) {
        this.BookList.remove(book);
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
