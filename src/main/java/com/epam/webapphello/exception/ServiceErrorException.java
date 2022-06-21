package com.epam.webapphello.exception;

public class ServiceErrorException extends Exception {
    public ServiceErrorException(Exception e) {
        super(e);
    }
    public ServiceErrorException(String message) {
        super(message);
    }
}
