package hkmu.comps380f.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String author;
    private double price;
    private String description;
    private int availability;

    //private Map<Integer, Comment> comments = new ConcurrentHashMap<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private ArrayList<Attachment> attachments = new ArrayList<>();

    // getter setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }


    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void deleteAttachment(Attachment attachment) {
        attachment.setBook(null);
        this.attachments.remove(attachment);
    }

    public int getNumberOfAttachments() {
        return this.attachments.size();
    }
    /*
    public Map<Integer, Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.put(this.comments.size(), comment);
    }

    public Comment getComment(int i) {
        return this.comments.get(i);
    }

    public void setComment(int i, Comment comment) {
        this.comments.put(i, comment);
    }

    public void setComments(Map<Integer, Comment> comments) {
        this.comments = comments;
    }

    public void setAttachments(Map<String, Attachment> attachments) {
        this.attachments = attachments;
    }*/
}