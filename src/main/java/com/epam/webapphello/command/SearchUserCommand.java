package com.epam.webapphello.command;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;;
import com.epam.webapphello.service.MedicineServiceImpl;
import com.epam.webapphello.service.UserService;
import com.epam.webapphello.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchUserCommand implements Command {

    private final UserService userService;

    private static final String SEARCH_USER_PARAMETER = "search-user";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorSearchUserMessage";
    private static final String ERROR_MESSAGE = "User is not found";
    private static final String USERS_STORAGE_ATTRIBUTE = "usersStorage";
    private static final String USERS_STORAGE_PAGE = "/WEB-INF/view/usersStorage.jsp";



    public SearchUserCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String searchingUser = req.getParameter(SEARCH_USER_PARAMETER);
        List<User> foundUser = null;
        foundUser = userService.findUserBySurname(searchingUser);
        if (foundUser.isEmpty()){
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
        }
        req.setAttribute(USERS_STORAGE_ATTRIBUTE, foundUser);
        CommandResult result = CommandResult.forward(USERS_STORAGE_PAGE);
        return result;
    }
}
