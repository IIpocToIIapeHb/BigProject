package com.epam.webapphello.entity;

import java.io.Serializable;

public class Medicine implements Identifable, Serializable {

    public static final long serialVersionUID = 8949699114079568267L;

    public final static String TABLE = "medicine";
    public final static String NAME = "name";
    public final static String DOSAGE = "dosage";
    public final static String WITH_RECIPE = "with_recipe";
    public final static String FORM = "form";
    public final static String AMOUNT = "amount";
    public final static String PACKAGE_AMOUNT = "packageAmount";
    public final static String PRICE = "price";


    private Long id;
    private String name;
    private Integer dosage;
    private boolean withRecipe;
    private String form;
    private Integer amount;
    private Integer packageAmount;
    private Double price;
    private String path;

    public Medicine(Long id, String name, Integer dosage, boolean withRecipe, String form, Integer amount, Integer packageAmount, Double price, String path) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.withRecipe = withRecipe;
        this.form = form;
        this.amount = amount;
        this.packageAmount = packageAmount;
        this.price = price;
        this.path = path;
    }

    public Medicine(String name, Integer dosage, boolean withRecipe, String form, Integer amount, Integer packageAmount, Double price) {
        this.name = name;
        this.dosage = dosage;
        this.withRecipe = withRecipe;
        this.form = form;
        this.amount = amount;
        this.packageAmount = packageAmount;
        this.price = price;
    }

    @Override
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

    public Integer getDosage() {
        return dosage;
    }

    public void setDosage(Integer dosage) {
        this.dosage = dosage;
    }

    public boolean getWithRecipe() {
        return withRecipe;
    }

    public void setWithRecipe(boolean with_recipe) {
        this.withRecipe = with_recipe;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(Integer package_amount) {
        this.packageAmount = package_amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosage=" + dosage +
                ", with_recipe=" + withRecipe +
                ", form='" + form + '\'' +
                ", amount=" + amount +
                ", package_amount=" + packageAmount +
                ", price=" + price +
                '}';
    }
}
