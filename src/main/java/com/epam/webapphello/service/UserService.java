package com.epam.webapphello.service;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService{
    Optional<User> login(String login, String password) throws ServiceException;
    List<User> findUserBySurname(String searchingUser) throws ServiceException;
    List<User> changeLockStatus(long parseLong) throws ServiceException;
}
