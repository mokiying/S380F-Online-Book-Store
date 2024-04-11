package hkmu.comps380f.model;


import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Comment {

    @Id
    private long id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private User user;
    private String content;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
