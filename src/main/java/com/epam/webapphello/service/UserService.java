package com.epam.webapphello.service;

import com.google.protobuf.ServiceException;

public interface UserService {

    boolean login(String login, String password) throws ServiceException;
}
