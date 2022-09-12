package com.epam.webapphello.command;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService userService;

    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String LANG_ATTRIBUTE = "lang";
    private static final String EN_LANG = "en";
    private static final String USER_ATTRIBUTE = "user";
    private static final String MAIN_PAGE_PAGE_COMMAND = "controller?command=mainPage";
    private static final String INDEX_PAGE_PATH = "/index.jsp";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_MESSAGE = "Invalid credentials";


    public LoginCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);

        req.getSession().setAttribute(LANG_ATTRIBUTE, EN_LANG);

        Optional<User> user = null;
        user = userService.login(login, password);

        CommandResult result;
        if (user.isPresent()) {
            req.getSession().setAttribute(USER_ATTRIBUTE, user.get());
            result = CommandResult.redirect(MAIN_PAGE_PAGE_COMMAND);

        } else {
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            result = CommandResult.forward(INDEX_PAGE_PATH);
        }
        return result;
    }
}
