package com.epam.webapphello.entity;

import java.io.Serializable;

public class MedicineCategory implements Identifable, Serializable {

    public static final long serialVersionUID = -648863109678500797L;

    public final static String TABLE = "medicine_category";
    public final static String CATEGORY_ID = "id";
    public final static String CATEGORY_NAME = "name";



    private Long id;
    private String name;

    public MedicineCategory(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineCategory that = (MedicineCategory) o;

        if (!id.equals(that.id)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MedicineCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
