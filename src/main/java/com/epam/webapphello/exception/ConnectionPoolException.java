package com.epam.webapphello.exception;

public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message,e);
    }
    }

