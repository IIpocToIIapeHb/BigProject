package com.epam.webapphello.command;

import com.epam.webapphello.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageCommand  implements Command {

    String page;

    public ShowPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        CommandResult result = CommandResult.forward(page);
        return result;
    }
}
