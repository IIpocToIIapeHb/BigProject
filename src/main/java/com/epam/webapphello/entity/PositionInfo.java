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
    public final static String  RECIPE_ID = "id";
    public final static String  RECIPE_STATUS = "status";
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
    private Byte medicineWithRecipe;
    private String medicinePath;
    private Integer requiredAmount;
    private Long recipeId;
    private String recipeStatus;
    private Date recipeValidUntil;
    private Double medicinePrice;
    private Double total;

    public PositionInfo(Long id, Long user_id, String name,
                        String surname, Long orderId, Date date,
                        String status, Long medicineId, String medicineName,
                        Byte medicineWithRecipe, String medicinePath,
                        Integer requiredAmount, Long recipeId,
                        String recipeStatus, Date recipeValidUntil,
                        Double medicinePrice) {
        this.id = id;
        this.userId = user_id;
        this.name = name;
        this.surname = surname;
        this.orderId = orderId;
        this.date = date;
        this.status = status;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicinePath = medicinePath;
        this.requiredAmount = requiredAmount;
        this.recipeId = recipeId;
        this.recipeStatus = recipeStatus;
        this.recipeValidUntil = recipeValidUntil;
        this.medicinePrice = medicinePrice;
        this.medicineWithRecipe = medicineWithRecipe;
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

    public Byte getMedicineWithRecipe() {
        return medicineWithRecipe;
    }

    public void setMedicineWithRecipe(Byte MedicineWithRecipe) {
        this.medicineWithRecipe = medicineWithRecipe;
    }

    public String getStringMedicineWithRecipe(){
        String result = medicineWithRecipe==0 ? "no":"yes";
        return  result;
    }

    public String getMedicinePath() {
        return medicinePath;
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

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeStatus() {
        return recipeStatus;
    }

    public void setRecipeStatus(String recipeStatus) {
        this.recipeStatus = recipeStatus;
    }

    public Date getRecipeValidUntil() {
        return recipeValidUntil;
    }

    public void setRecipeValidUntil(Date recipeValidUntil) {
        this.recipeValidUntil = recipeValidUntil;
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
