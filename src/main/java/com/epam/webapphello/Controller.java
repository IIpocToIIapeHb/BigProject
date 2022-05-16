package com.epam.webapphello;


import com.epam.webapphello.command.Command;
import com.epam.webapphello.command.CommandFactory;
import com.epam.webapphello.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandLine = req.getParameter("command");
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.createCommand(commandLine);
        String page = null;
        try {
            page = command.execute(req, resp);
            req.getRequestDispatcher(page).forward(req, resp);
        } catch (CommandException e) {
            LOGGER.error(e);
        }
    }
}
