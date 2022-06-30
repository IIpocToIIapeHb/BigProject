package com.epam.webapphello.entity;

import java.io.Serializable;
import java.sql.Date;

public class PrescriptionInfo implements Identifable, Serializable {

    public static final long serialVersionUID = 6081419106604946710L;


    public final static String PRESCRIPTION_ID = "id";
    public final static String USER_NAME = "user.name";
    public final static String USER_SURNAME = "surname";
    public final static String USER_BIRTH = "birth";
    public final static String MEDICINE_NAME = "medicine.name";
    public final static String PRESCRIPTION_VALID_UNTIL = "valid_until";
    public final static String PRESCRIPTION_STATUS = "status";
    public final static String PRESCRIPTION_MEDICINE_AMOUNT = "amount";


    private long PrescriptionId;
    private String userName;
    private String userSurname;
    private Date userBirth;
    private String medicineName;
    private Date prescriptionValidUntil;
    private String prescriptionStatus;
    private int prescriptionMedicineAmount;

    public PrescriptionInfo(long prescriptionId, String userName, String userSurname, Date userBirth,String medicineName, Date prescriptionValidUntil, String prescriptionStatus, int prescriptionMedicineAmount) {
        PrescriptionId = prescriptionId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userBirth = userBirth;
        this.medicineName = medicineName;
        this.prescriptionValidUntil = prescriptionValidUntil;
        this.prescriptionStatus = prescriptionStatus;
        this.prescriptionMedicineAmount = prescriptionMedicineAmount;
    }

    public Long getId() {
        return PrescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        PrescriptionId = prescriptionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    public Date getPrescriptionValidUntil() {
        return prescriptionValidUntil;
    }

    public void setPrescriptionValidUntil(Date prescriptionValidUntil) {
        this.prescriptionValidUntil = prescriptionValidUntil;
    }

    public String getPrescriptionStatus() {
        return prescriptionStatus;
    }

    public void setPrescriptionStatus(String prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

    public int getPrescriptionMedicineAmount() {
        return prescriptionMedicineAmount;
    }

    public void setPrescriptionMedicineAmount(int prescriptionMedicineAmount) {
        this.prescriptionMedicineAmount = prescriptionMedicineAmount;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrescriptionInfo that = (PrescriptionInfo) o;

        if (PrescriptionId != that.PrescriptionId) return false;
        if (prescriptionMedicineAmount != that.prescriptionMedicineAmount) return false;
        if (!userName.equals(that.userName)) return false;
        if (!userSurname.equals(that.userSurname)) return false;
        if (!userBirth.equals(that.userBirth)) return false;
        if (!medicineName.equals(that.medicineName)) return false;
        if (!prescriptionValidUntil.equals(that.prescriptionValidUntil)) return false;
        return prescriptionStatus.equals(that.prescriptionStatus);
    }

    @Override
    public int hashCode() {
        int result = (int) (PrescriptionId ^ (PrescriptionId >>> 32));
        result = 31 * result + userName.hashCode();
        result = 31 * result + userSurname.hashCode();
        result = 31 * result + userBirth.hashCode();
        result = 31 * result + medicineName.hashCode();
        result = 31 * result + prescriptionValidUntil.hashCode();
        result = 31 * result + prescriptionStatus.hashCode();
        result = 31 * result + prescriptionMedicineAmount;
        return result;
    }

    @Override
    public String toString() {
        return "PrescriptionInfo{" +
                "PrescriptionId=" + PrescriptionId +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userBirth=" + userBirth +
                ", medicineName='" + medicineName + '\'' +
                ", prescriptionValidUntil=" + prescriptionValidUntil +
                ", prescriptionStatus='" + prescriptionStatus + '\'' +
                ", prescriptionMedicineAmount=" + prescriptionMedicineAmount +
                '}';
    }
}
