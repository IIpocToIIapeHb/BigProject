package com.epam.webapphello.service;

import com.epam.webapphello.exception.DAOException;

public class ServiceException extends Throwable {
    public ServiceException(DAOException e) {
        super(e);
    }
}
