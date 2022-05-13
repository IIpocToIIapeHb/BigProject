package com.epam.webapphello.dao;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;

import java.util.Optional;

public interface UserDao {
    public Optional<User> findUserByLoginAndPassword(final String login, final String password) throws DAOException;
}
