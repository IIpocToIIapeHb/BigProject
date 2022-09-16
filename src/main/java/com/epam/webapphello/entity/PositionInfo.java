package com.epam.webapphello.entity;

import java.io.Serializable;
import java.sql.Date;

public class PositionInfo implements Identifable, Serializable {

    public static final long serialVersionUID = 5261029874237852921L;

    public final static String ORDER_MEDICINE_ID = "id";
    public final static String USER_ID = "user_id";
    public final static String NAME = "name";
    public final static String SURNAME = "surname";
    public final static String ORDER_ID = "order_id";
    public final static String CREATION_DATE = "creation_date";
    public final static String STATUS = "status";
    public final static String MEDICINE_ID = "medicine_id";
    public final static String  MEDICINE_NAME = "Medicine.name";
    public final static String  MEDICINE_WITH_RECIPE = "with_recipe";
    public final static String  MEDICINE_PATH = "path";
    public final static String  REQUIRED_AMOUNT = "required_amount";
    public final static String  RECIPE_ID = "recipe.id";
    public final static String  RECIPE_STATUS = "recipe.status";
    public final static String  RECIPE_AMOUNT = "recipe.amount";
    public final static String  RECIPE_VALID_UNTIL = "valid_until";
    public final static String  MEDICINE_PRICE = "price";



    private Long id;
    private Long userId;
    private String name;
    private String surname;
    private Long orderId;
    private Date date;
    private String status;
    private Long medicineId;
    private String medicineName;
    private Byte medicineWithPrescription;
    private String medicinePath;
    private Integer requiredAmount;
    private Long prescriptionId;
    private String prescriptionStatus;
    private Integer prescriptionAmount;
    private Date prescriptionValidUntil;
    private Double medicinePrice;
    private Double total;

    public PositionInfo(Long id, Long userId, String name,
                        String surname, Long orderId, Date date,
                        String status, Long medicineId, String medicineName,
                        Byte medicineWithPrescription, String medicinePath,
                        Integer requiredAmount, Long prescriptionId,
                        String prescriptionStatus, Integer prescriptionAmount, Date prescriptionValidUntil,
                        Double medicinePrice) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicinePath = medicinePath;
        this.requiredAmount = requiredAmount;
        this.prescriptionId = prescriptionId;
        this.prescriptionStatus = prescriptionStatus;
        this.prescriptionAmount = prescriptionAmount;
        this.prescriptionValidUntil = prescriptionValidUntil;
        this.medicinePrice = medicinePrice;
        this.medicineWithPrescription = medicineWithPrescription;
        total = requiredAmount * medicinePrice;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Byte getMedicineWithPrescription() {
        return medicineWithPrescription;
    }

    public void setMedicineWithPrescription(Byte medicineWithPrescription) {
        this.medicineWithPrescription = medicineWithPrescription;
    }

    public String getStringMedicineWithPrescription(){
        String result = medicineWithPrescription ==0 ? "no":"yes";
        return  result;
    }

    public String getMedicinePath() {
        return medicinePath;
    }

    public String getFullMedicinePath() {
        return "static/img/storage/"+medicinePath;
    }

    public void setMedicinePath(String medicinePath) {
        this.medicinePath = medicinePath;
    }

    public Integer getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(Integer requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionStatus() {

        return prescriptionStatus;
    }

    public void setPrescriptionStatus(String prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

    public Integer getPrescriptionAmount() {
        return prescriptionAmount;
    }

    public void setPrescriptionAmount(Integer prescriptionAmount) {
        this.prescriptionAmount = prescriptionAmount;
    }

    public Date getPrescriptionValidUntil() {
        return prescriptionValidUntil;
    }

    public void setPrescriptionValidUntil(Date prescriptionValidUntil) {
        this.prescriptionValidUntil = prescriptionValidUntil;
    }

    public Double getMedicinePrice() {
        return medicinePrice;
    }

    public void setMedicinePrice(Double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
