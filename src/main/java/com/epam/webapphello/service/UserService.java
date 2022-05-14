package com.epam.webapphello.service;

import com.epam.webapphello.entity.Identifable;
import com.epam.webapphello.entity.User;
import com.google.protobuf.ServiceException;

import java.util.Optional;

public interface UserService{
    Optional<User> login(String login, String password);
}
