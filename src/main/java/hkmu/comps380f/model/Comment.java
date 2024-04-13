package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username", insertable=false, updatable=false)
    private String username;
    @ManyToOne
    @JoinColumn(name = "username")
    private BookUser bookUser;
    @Column(name = "content")
    private String content;
    @Column(name = "book_id", insertable=false, updatable=false)
    private long bookId;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
