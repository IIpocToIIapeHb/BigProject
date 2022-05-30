package com.epam.webapphello.command;

import com.epam.webapphello.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCartCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicine_id = req.getParameter("medicine-id");
        String medicine_number = req.getParameter("medicine-number");
    }
}
