package com.epam.webapphello.exception;

import com.epam.webapphello.service.ServiceException;

public class CommandException extends Throwable {
    public CommandException(ServiceException e) {
        super(e);
    }
}
