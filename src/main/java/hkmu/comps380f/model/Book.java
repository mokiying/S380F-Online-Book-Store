package hkmu.comps380f.model;

import jakarta.persistence.*;
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

    @OneToMany(cascade = CascadeType.ALL)
    @MapKeyColumn(name = "comment_id")
    private Map<Integer, Comment> comments = new ConcurrentHashMap<>();

    @OneToMany(cascade = CascadeType.ALL)
    @MapKeyColumn(name = "attachment_name")
    private Map<String, Attachment> attachments = new ConcurrentHashMap<>();

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

    public Attachment getAttachment(String name) {
        return this.attachments.get(name);
    }

    public Collection<Attachment> getAttachments() {
        return this.attachments.values();
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.put(attachment.getName(), attachment);
    }

    public int getNumberOfAttachments() {
        return this.attachments.size();
    }

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
    }
}