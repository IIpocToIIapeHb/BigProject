package com.epam.webapphello.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class User implements Identifable, Serializable {

    public static final long serialVersionUID = 4455244596435242543L;

    public final static String TABLE = "user";
    public final static String ROLE = "role";
    public final static String AMOUNT = "amount";
    public final static String SURNAME = "surname";
    public final static String NAME = "name";
    public final static String BIRTH = "birth";
    public final static String  IS_BLOCKED= "is_blocked";

    private Long id;
    private String role;
    private BigDecimal amount;
    private String surname;
    private String name;
    private boolean isBlocked;
    private Date birth;

    public User(Long id, String role, BigDecimal amount) {
        this.id = id;
        this.role = role;
        this.amount = amount;

    }

    public User(Long id, String role, BigDecimal amount, String surname, String name, boolean isBlocked, Date birth) {
        this.id = id;
        this.role = role;
        this.amount = amount;
        this.surname = surname;
        this.name = name;
        this.isBlocked = isBlocked;
        this.birth = birth;
    }

    public User() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", amount=" + amount +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", isBlocked=" + isBlocked +
                ", birth=" + birth +
                '}';
    }
}
