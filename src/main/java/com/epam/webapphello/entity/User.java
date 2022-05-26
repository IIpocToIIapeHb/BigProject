package com.epam.webapphello.entity;

import java.io.Serializable;

public class User implements Identifable, Serializable {

    public static final long serialVersionUID = 4455244596435242543L;

    public final static String TABLE = "user";
    public final static String ROLE = "role";

    private Long id;
    private String role;

    public User(Long id, String role) {
        this.id = id;
        this.role = role;

    }

    public User() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
