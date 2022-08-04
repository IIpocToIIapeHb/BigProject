package com.epam.webapphello.service;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderInfo;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderInfoService {

    List<OrderInfo> getAllPaidOrders() throws ServiceException;

    Optional<OrderInfo> findOrderInfoById(long parseLong) throws ServiceException;
}
