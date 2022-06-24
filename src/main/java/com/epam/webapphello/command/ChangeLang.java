package com.epam.webapphello.command;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

public class ChangeLang implements Command {


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse resp) throws ServiceException {
        String language = request.getParameter("lang");
        String page = request.getHeader("referer");
        CommandResult result = null;
        switch (language){
        case "ru":
            Locale.forLanguageTag("ru");
            request.getSession().setAttribute("lang", "ru");
            break;
        case "en":
             request.getSession().setAttribute("lang", "en");
             break;
        case "fr":
             request.getSession().setAttribute("lang", "fr");
             break;
            default:
                throw new IllegalArgumentException("Unknown language" + language);
        }
         result = CommandResult.redirect(page);
        return result;
    }
}
