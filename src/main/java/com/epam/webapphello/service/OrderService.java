package com.epam.webapphello.service;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.exception.ServiceException;

import java.util.Optional;

public interface OrderService {
    Optional<Order> findOrderByStatusAndUser(final String status, final Long userId) throws ServiceException;
    void save(Order item, Long medicineId, Integer requiredAmount,Long userId, boolean isPrescriptionRequired) throws ServiceException;
    void performOrder(long orderId) throws ServiceException;;
}
