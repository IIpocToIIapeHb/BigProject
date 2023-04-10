package com.epam.webapphello;


import com.epam.webapphello.command.Command;
import com.epam.webapphello.command.CommandFactory;
import com.epam.webapphello.command.CommandResult;
import com.epam.webapphello.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(Controller.class);
    private static final String COMMAND_PARAMETER = "command";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_MESSAGE_PAGE_PATH = "/error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandLine = req.getParameter(COMMAND_PARAMETER);
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.createCommand(commandLine);
        CommandResult result = null;
        try {
            result = command.execute(req,resp);
            dispatch(req,resp, result);
        } catch (ServiceException  e) {
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
            dispatch(req,resp, CommandResult.forward(ERROR_MESSAGE_PAGE_PATH));
        }
   }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult result) throws ServletException, IOException {
        String page = result.getPage();
        if(!result.isRedirect()){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req,resp);
        } else {
            resp.sendRedirect(page);
        }
    }
}
