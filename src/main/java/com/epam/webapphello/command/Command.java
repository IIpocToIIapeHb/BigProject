package com.epam.webapphello.command;

import com.epam.webapphello.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String execute (HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}
