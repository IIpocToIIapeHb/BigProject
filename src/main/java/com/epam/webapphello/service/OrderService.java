package com.epam.webapphello.service;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;

import java.util.Optional;

public interface OrderService {
    Optional<Order> findOrderByStatusAndUser(final String status, final Long user_id) throws ServiceException;
    void save(Order item, Long medicine_id, Integer required_amount,Long userId, boolean isPrescriptionRequired) throws ServiceException;

    void performOrder(long orderId) throws ServiceException;;
}
