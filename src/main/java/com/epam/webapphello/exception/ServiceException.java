package com.epam.webapphello.exception;

public class ServiceException extends Exception {
    public ServiceException(Exception e) {
        super(e);
    }
}
