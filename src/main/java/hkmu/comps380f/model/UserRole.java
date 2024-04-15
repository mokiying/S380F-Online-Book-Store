package hkmu.comps380f.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private int id;
    @Column(insertable = false, updatable = false)
    private String username;
    private String role;
    @ManyToOne
    @JoinColumn(name = "username")
    private BookUser bookUser;
    public UserRole() {
    }
    public UserRole(BookUser bookUser, String role) {
        this.bookUser = bookUser;
        this.role = role;
    }
    // getters and setters of all properties

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BookUser getUser() {
        return bookUser;
    }

    public void setUser(BookUser bookUser) {
        this.bookUser = bookUser;
    }
}
