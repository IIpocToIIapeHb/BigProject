package com.epam.webapphello.command;

import com.epam.webapphello.entity.User;
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
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> user = null;
        user = userService.login(login, password);

        CommandResult result;
        if (user.isPresent()) {
          //  req.getSession().setAttribute("user", user.get());
            result = CommandResult.redirect("controller?command=mainPage");

        } else {
            req.setAttribute("errorMessage", "Invalid credentials");
            result = CommandResult.forward("/index.jsp");

//
//            if (user.isPresent()) {
//                  req.getSession().setAttribute("user", user.get());
//                  return "WEB-INF/view/main.jsp";
//            } else {
//                req.setAttribute("errorMessage", "Invalid credentials");
//                 return "index.jsp";

        }
        return result;
    }
}
