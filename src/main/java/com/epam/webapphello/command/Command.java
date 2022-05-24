package com.epam.webapphello.command;

import com.epam.webapphello.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute (HttpServletRequest req, HttpServletResponse resp) throws ServiceException;
}
