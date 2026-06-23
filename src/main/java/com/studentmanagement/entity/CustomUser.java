package com.studentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class CustomUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    public CustomUser() {

    }

    public CustomUser(String username, String password, String role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public CustomUser(int id, String username, String password, String role) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

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

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    @Override
    public String toString() {

        return "CustomUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
