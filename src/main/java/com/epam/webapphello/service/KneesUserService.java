package com.epam.webapphello.service;

import com.epam.webapphello.dao.UserDao;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;


import java.util.Optional;

public class KneesUserService implements UserService{

    private UserDao dao;

    public KneesUserService(UserDao dao){
        this.dao = dao;
    }
    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            Optional<User> user = dao.findUserByLoginAndPassword(login,password);
            return user;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
