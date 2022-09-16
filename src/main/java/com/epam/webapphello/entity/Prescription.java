package com.epam.webapphello.entity;

import java.io.Serializable;
import java.sql.Date;

public class Prescription implements Identifable, Serializable {

    public static final long serialVersionUID = -368849885493883294L;

    public final static String TABLE = "recipe";
    public final static String ID = "id";
    public final static String USER_ID = "user_id";
    public final static String MEDICINE_ID = "medicine_id";
    public final static String VALID_UNTIL = "valid_until";
    public final static String STATUS = "status";
    public final static String AMOUNT = "amount";


    private Long id;
    private Long userId;
    private Long medicineId;
    private Date validUntil;
    private String status;
    private Integer amount;

    public Prescription(Long id, Long userId, Long medicineId, Date validUntil, String status, Integer amount) {
        this.id = id;
        this.userId = userId;
        this.medicineId = medicineId;
        this.validUntil = validUntil;
        this.status = status;
        this.amount = amount;
    }

    public Prescription(Long userId, Long medicineId, String status) {
        this.userId = userId;
        this.medicineId = medicineId;
        this.status = status;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prescription)) return false;

        Prescription prescription = (Prescription) o;

        if (getId() != null ? !getId().equals(prescription.getId()) : prescription.getId() != null) return false;
        if (getUserId() != null ? !getUserId().equals(prescription.getUserId()) : prescription.getUserId() != null) return false;
        if (getMedicineId() != null ? !getMedicineId().equals(prescription.getMedicineId()) : prescription.getMedicineId() != null)
            return false;
        if (getValidUntil() != null ? !getValidUntil().equals(prescription.getValidUntil()) : prescription.getValidUntil() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(prescription.getStatus()) : prescription.getStatus() != null) return false;
        return getAmount() != null ? getAmount().equals(prescription.getAmount()) : prescription.getAmount() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getMedicineId() != null ? getMedicineId().hashCode() : 0);
        result = 31 * result + (getValidUntil() != null ? getValidUntil().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", userId=" + userId +
                ", medicineId=" + medicineId +
                ", validUntil=" + validUntil +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                '}';
    }
}
