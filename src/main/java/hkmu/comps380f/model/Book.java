package hkmu.comps380f.model;

public class Book {
    private String name;
    private String author;
    private double price;
    private String description;
    private String coverPhoto;
    private boolean availability;

    public Book(String name, String author, double price, String description, String coverPhoto, boolean availability) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.coverPhoto = coverPhoto;
        this.availability = availability;
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

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
