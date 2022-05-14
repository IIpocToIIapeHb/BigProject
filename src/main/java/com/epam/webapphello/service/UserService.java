package com.epam.webapphello.service;

import com.epam.webapphello.entity.User;
import com.google.protobuf.ServiceException;

import java.util.Optional;

public interface UserService {

    boolean login(String login, String password);
}
