package com.epam.webapphello.entity;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Identifable, Serializable {

    public static final long serialVersionUID = 6251028556816113956L;

    public final static String TABLE = "Pharmacy.order";
    public final static String USER_ID = "user_id";
    public final static String CREATION_DATE = "creation_date";
    public final static String STATUS = "status";


    private Long id;
    private Long user_id;
    private Date date;
    private String status;

    public Order(Long user_id, Date date, String status) {
        this.user_id = user_id;
        this.date = date;
        this.status = status;
    }

    public Order(Long id, Long user_id, Date date, String status) {
        this.id = id;
        this.user_id = user_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
                ", user_id=" + user_id +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
