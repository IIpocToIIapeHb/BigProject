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
    public final static String PACKAGE_AMOUNT = "package_amount";
    public final static String PRICE = "price";


    private Long id;
    private String name;
    private Integer dosage;
    private Byte with_recipe;
    private String form;
    private Integer amount;
    private Integer package_amount;
    private Double price;

    public Medicine(Long id, String name, Integer dosage, Byte with_recipe, String form, Integer amount, Integer package_amount, Double price) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.with_recipe = with_recipe;
        this.form = form;
        this.amount = amount;
        this.package_amount = package_amount;
        this.price = price;
    }

    public Medicine(String name, Integer dosage, Byte with_recipe, String form, Integer amount, Integer package_amount, Double price) {
        this.name = name;
        this.dosage = dosage;
        this.with_recipe = with_recipe;
        this.form = form;
        this.amount = amount;
        this.package_amount = package_amount;
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

    public Byte getWith_recipe() {
        return with_recipe;
    }

    public void setWith_recipe(Byte with_recipe) {
        this.with_recipe = with_recipe;
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

    public Integer getPackage_amount() {
        return package_amount;
    }

    public void setPackage_amount(Integer package_amount) {
        this.package_amount = package_amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosage=" + dosage +
                ", with_recipe=" + with_recipe +
                ", form='" + form + '\'' +
                ", amount=" + amount +
                ", package_amount=" + package_amount +
                ", price=" + price +
                '}';
    }
}
