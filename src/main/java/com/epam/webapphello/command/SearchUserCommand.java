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

    public SearchUserCommand(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String searchingUser = req.getParameter("search-user");
        List<User> foundUser = null;
        foundUser = userService.findUserBySurname(searchingUser);
        if (foundUser.isEmpty()){

            req.setAttribute("errorSearchUserMessage", "User is not found");
        }

        req.setAttribute("usersStorage", foundUser);
        CommandResult result = CommandResult.forward("/WEB-INF/view/usersStorage.jsp");
        return result;
    }
}
