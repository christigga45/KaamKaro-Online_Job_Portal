package model;

import java.sql.Timestamp;

public class Admin {
    
    private long adminId;
    private String username;
    private String email;
    private String password;
    private Timestamp createdAt;

    // Default constructor
    public Admin() {
    }

    // Constructor with parameters
    public Admin(long adminId, String username, String email, String password, Timestamp createdAt) {
        this.adminId = adminId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    // Getter and Setter methods

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Override toString() method for better display
    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", username=" + username + ", email=" + email + ", password=" + password
                + ", createdAt=" + createdAt + "]";
    }
}
