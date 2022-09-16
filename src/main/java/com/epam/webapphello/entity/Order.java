package com.epam.webapphello.entity;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Identifable, Serializable {

    public static final long serialVersionUID = 3244626632322455839L;

    public final static String TABLE = "Pharmacy.order";
    public final static String USER_ID = "user_id";
    public final static String CREATION_DATE = "creation_date";
    public final static String STATUS = "status";


    private Long id;
    private Long userId;
    private Date date;
    private String status;

    public Order(Long userId, Date date, String status) {
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    public Order(Long id, Long userId, Date date, String status) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
