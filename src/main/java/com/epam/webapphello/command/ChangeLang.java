package com.epam.webapphello.command;

import com.epam.webapphello.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class ChangeLang implements Command {

    private static final String REQUEST_HEADER = "referer";
    private static final String LANG = "lang";
    private static final String RU_LANG = "ru";
    private static final String EN_LANG = "en";
    private static final String FR_LANG = "fr";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse resp) throws ServiceException {
        String language = request.getParameter(LANG);
        String page = request.getHeader(REQUEST_HEADER);
        CommandResult result = null;
        switch (language){
        case "ru":
            Locale.forLanguageTag(RU_LANG);
            request.getSession().setAttribute(LANG, RU_LANG);
            break;
        case "en":
             request.getSession().setAttribute(LANG, EN_LANG);
             break;
        case "fr":
             request.getSession().setAttribute(LANG, FR_LANG);
             break;
            default:
                throw new IllegalArgumentException("Unknown language" + language);
        }
         result = CommandResult.redirect(page);
        return result;
    }
}
