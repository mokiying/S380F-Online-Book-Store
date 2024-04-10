package hkmu.comps380f.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Book {
    private long id;
    private String name;
    private String author;
    private double price;
    private String description;
    private boolean available;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Attachment getAttachment(String name) { return this.attachments.get(name); }

    public Collection<Attachment> getAttachments() { return this.attachments.values(); }
    public void addAttachment(Attachment attachment) { this.attachments.put(attachment.getId(), attachment); }
    public int getNumberOfAttachments() { return this.attachments.size(); }
}
