package com.epam.webapphello.command;

import com.epam.webapphello.service.UserService;
import com.google.protobuf.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        return null;
    }

//    @Override
//    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        if (userService.login(login,password)!=){
//            req.getSession().setAttribute("user", "admin");
//         //   req.setAttribute("user", "admin");
//            return "WEB-INF/view/main.jsp";
//        } else {
//            req.setAttribute("errorMessage", "Invalid credentials");
//            return "index.jsp";
//        }
//    }
}
