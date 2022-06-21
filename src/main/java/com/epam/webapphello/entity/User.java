package com.epam.webapphello.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Identifable, Serializable {

    public static final long serialVersionUID = 4455244596435242543L;

    public final static String TABLE = "user";
    public final static String ROLE = "role";
    public final static String AMOUNT = "amount";

    private Long id;
    private String role;
    private BigDecimal amount;

    public User(Long id, String role, BigDecimal amount) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
