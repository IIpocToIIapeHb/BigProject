package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;

import java.util.Optional;

public interface OrderDao {
    public Optional<Order> findOrderByStatusAndUser(final String status, final Long user_id) throws DAOException;
}
