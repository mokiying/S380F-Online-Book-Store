package hkmu.comps380f.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;


//@Entity
public class Cart {
    @Id
    private long id;
    @Column(name = "username", insertable=false, updatable=false)
    private String username;
    @OneToOne
    @JoinColumn(name = "username")
    private BookUser bookUser;
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<BookItem> bookItems = new ArrayList<>();

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

    public BookUser getUser() {
        return bookUser;
    }

    public void setUser(BookUser bookUser) {
        this.bookUser = bookUser;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }
}
