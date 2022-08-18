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

    public ChangeLockUserStatus(UserService userService) {
        this.userService = userService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String userId = req.getParameter("user-id");




        CommandResult result=null;
       List<User> foundUser =  userService.changeLockStatus(Long.parseLong(userId));
        req.setAttribute("usersStorage", foundUser);
        result = CommandResult.forward("/WEB-INF/view/usersStorage.jsp");
        return result;
    }
}
