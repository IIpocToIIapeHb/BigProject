package com.epam.webapphello.entity;

import java.io.Serializable;

public class User implements Identifable, Serializable {

    public static final long serialVersionUID = 4455244596435242543L;

    public final static String TABLE = "user";
    public final static String NAME = "name";
    public final static String SURNAME = "surname";
    public final static String ROLE = "role";
    public final static String AMOUNT = "amount";

    private Long id;
    private String name;
    private String surname;
    private String role;
    private double amount;
    private boolean isBlocked;

    public User(Long id, String name, String surname, String login, String password, String role, double amount, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.amount = amount;
        this.isBlocked = isBlocked;
    }

    public User(Long id, String name, String login) {
        this.id = id;
        this.name = name;

    }

    public User(String name, String surname, String role, double amount) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.amount = amount;
    }
    public User(String name, String surname,String login, String password, String role, double amount) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.amount = amount;
    }

    public User(String name, String surname, String role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public User(Long id,String name, String surname, String role, double amount) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.amount = amount;
    }

    public User() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }




    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
