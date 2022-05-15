package com.epam.webapphello.exception;

public class ConnectionException extends Exception {
    public ConnectionException(Exception e) {
        super(e);
    }

    public ConnectionException(String message, Exception e) {
        super(message,e);
    }
    }

