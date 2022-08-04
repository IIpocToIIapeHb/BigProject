package com.epam.webapphello.entity;

import java.io.Serializable;
import java.sql.Date;

public class OrderInfo implements Identifable, Serializable {

    public static final long serialVersionUID = 5625396934982566897L;

    public final static String TABLE = "Pharmacy.order";
    public final static String ORDER_ID = "id";
    public final static String USER_ID = "user_id";
    public final static String USER_NAME = "name";
    public final static String USER_SURNAME = "surname";
    public final static String USER_BIRTH = "birth";
    public final static String USER_ADDRESS = "address";
    public final static String CREATION_ORDER_DATE = "creation_date";
    public final static String CLIENT_STATUS = "status";
    public final static String DELIVERY_STATUS = "delivery_status";


    private Long orderId;
    private Long userId;
    private String userName;
    private String userSurname;
    private Date userBirth;
    private String userAddress;
    private Date creationDate;
    private String status;
    private String deliveryStatus;

    public OrderInfo(Long orderId, Long userId, String userName, String userSurname, Date userBirth, String userAddress, Date creationDate, String status, String deliveryStatus) {
        this.orderId = orderId;
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userBirth = userBirth;
        this.userAddress = userAddress;
        this.creationDate = creationDate;
        this.status = status;
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public Long getId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderInfo orderInfo = (OrderInfo) o;

        if (!orderId.equals(orderInfo.orderId)) return false;
        if (!userId.equals(orderInfo.userId)) return false;
        if (!userName.equals(orderInfo.userName)) return false;
        if (!userSurname.equals(orderInfo.userSurname)) return false;
        if (!userBirth.equals(orderInfo.userBirth)) return false;
        if (!userAddress.equals(orderInfo.userAddress)) return false;
        if (!creationDate.equals(orderInfo.creationDate)) return false;
        if (!status.equals(orderInfo.status)) return false;
        return deliveryStatus.equals(orderInfo.deliveryStatus);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + userSurname.hashCode();
        result = 31 * result + userBirth.hashCode();
        result = 31 * result + userAddress.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + deliveryStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", userName=" + userName +
                ", userSurname=" + userSurname +
                ", userBirth=" + userBirth +
                ", userAddress='" + userAddress + '\'' +
                ", creationDate=" + creationDate +
                ", status='" + status + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}
