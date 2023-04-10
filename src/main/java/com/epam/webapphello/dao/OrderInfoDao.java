package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderInfo;
import com.epam.webapphello.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface OrderInfoDao extends Dao<OrderInfo>{

    List<OrderInfo> getAllPaidOrders() throws DAOException;

    Optional<OrderInfo> getOrderInfoById(long orderInfoId)throws DAOException;
}
