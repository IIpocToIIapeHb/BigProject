package com.epam.webapphello.exception;

public class DAOException extends Exception {
    public DAOException(Exception e) {
        super(e);
    }
}
