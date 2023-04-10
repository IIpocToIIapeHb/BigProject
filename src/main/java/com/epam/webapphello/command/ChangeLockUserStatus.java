package com.epam.webapphello.command;

import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ChangeLockUserStatus implements Command {

    private final UserService userService;

    private static final String USER_ID_PARAMETER = "user-id";
    private static final String USERS_STORAGE_ATTRIBUTE = "usersStorage";
    private static final String USERS_STORAGE_PAGE_PATH = "/WEB-INF/view/usersStorage.jsp";

    public ChangeLockUserStatus(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String userId = req.getParameter(USER_ID_PARAMETER);
        CommandResult result=null;
        List<User> user =  userService.changeLockStatus(Long.parseLong(userId));
        req.setAttribute(USERS_STORAGE_ATTRIBUTE, user);
        result = CommandResult.forward(USERS_STORAGE_PAGE_PATH);
        return result;
    }
}
