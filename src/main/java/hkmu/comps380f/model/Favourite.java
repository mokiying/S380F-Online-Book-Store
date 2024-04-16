package hkmu.comps380f.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Favourite {
    @Id
    private long id;
    @Column(name = "username", insertable=false, updatable=false)
    private String username;
    @ManyToOne
    @JoinColumn(name = "username")
    private BookUser bookUser;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="favourites")
    private List<Book> book = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookUser getBookUser() {
        return bookUser;
    }

    public void setBookUser(BookUser bookUser) {
        this.bookUser = bookUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
