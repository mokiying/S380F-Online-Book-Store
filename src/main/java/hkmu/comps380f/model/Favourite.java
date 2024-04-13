package hkmu.comps380f.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;


//@Entity
public class Favourite {
    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "book_id")
    private User user;
    @Column(name = "username", insertable=false, updatable=false)
    private String username;
    @OneToMany(mappedBy = "favourite", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<BookItem> bookItems = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    public void setBookItems(List<BookItem> bookItems) {
        this.bookItems = bookItems;
    }
}
