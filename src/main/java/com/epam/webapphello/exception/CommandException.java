package com.epam.webapphello.exception;


public class CommandException extends Exception {
    public CommandException(ServiceException e) {
        super(e);
    }
}
