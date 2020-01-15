package io.agileintelligence.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank(message = "Must provide first name")
    private String firstName;
    @NotBlank(message = "Must provide a last name")
    private String lastName;
    @NotBlank(message = "Must provide an email address")
    private String email;
    @NotBlank(message = "Must provide a username")
    @Column(updatable = false, unique = true)
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @Column(updatable = false)
    private Date createdAt;
    @JsonFormat(pattern = "yyy-mm-dd")
    private Date updatedAt;

    public User() {
    }

    public User(@NotBlank(message = "Must provide first name") String firstName, @NotBlank(message = "Must provide a last name") String lastName, @NotBlank(message = "Must provide an email address") String email, @NotBlank(message = "Must provide a username") String username, @NotBlank(message = "Password is required") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
