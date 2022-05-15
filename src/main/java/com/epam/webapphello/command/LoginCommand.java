package com.epam.webapphello.command;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.CommandException;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> user = null;

        try {
            user = userService.login(login, password);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        if (user.isPresent()) {
            req.getSession().setAttribute("user", "admin");
            return "WEB-INF/view/main.jsp";
        } else {
            req.setAttribute("errorMessage", "Invalid credentials");
            return "index.jsp";
        }

    }
}
