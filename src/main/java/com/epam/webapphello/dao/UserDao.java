package com.epam.webapphello.dao;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User>{
    public Optional<User> findUserByLoginAndPassword(final String login, final String password) throws DAOException;

    List<User> findUserBySurname(String searchingUser) throws DAOException;
}
