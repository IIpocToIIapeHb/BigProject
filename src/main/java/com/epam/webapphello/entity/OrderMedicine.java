package com.epam.webapphello.entity;

import java.io.Serializable;

public class OrderMedicine implements Identifable, Serializable {

    public static final long serialVersionUID = -8633218305289729197L;

    public final static String TABLE = "order_medicine";
    public final static String MEDICINE_ID = "medicine_id";
    public final static String REQUIRED_AMOUNT = "required_amount";
    public final static String ORDER_ID = "order_id";

    private Long id;
    private Long medicineId;
    private Integer requiredAmount;
    private Long orderId;

    public OrderMedicine(Long id, Long medicineId, Integer requiredAmount, Long orderId) {
        this.id = id;
        this.medicineId = medicineId;
        this.requiredAmount = requiredAmount;
        this.orderId = orderId;
    }


    public OrderMedicine(Long medicineId, Integer requiredAmount, Long orderId) {
        this.id = id;
        this.medicineId = medicineId;
        this.requiredAmount = requiredAmount;
        this.orderId = orderId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(Integer requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
