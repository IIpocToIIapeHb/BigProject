package com.epam.webapphello.entity;

import java.io.Serializable;

public class OrderMedicine implements Identifable, Serializable {

    public static final long serialVersionUID = -4390825584280754489L;

    public final static String TABLE = "order_medicine";
    public final static String MEDICINE_ID = "medicine_id";
    public final static String REQUIRED_AMOUNT = "required_amount";
    public final static String ORDER_ID = "order_id";

    private Long id;
    private Integer medicine_id;
    private Integer required_amount;
    private Integer order_id;

    public OrderMedicine(Long id, Integer medicine_id, Integer required_amount, Integer order_id) {
        this.id = id;
        this.medicine_id = medicine_id;
        this.required_amount = required_amount;
        this.order_id = order_id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Integer medicine_id) {
        this.medicine_id = medicine_id;
    }

    public Integer getRequired_amount() {
        return required_amount;
    }

    public void setRequired_amount(Integer required_amount) {
        this.required_amount = required_amount;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
}
