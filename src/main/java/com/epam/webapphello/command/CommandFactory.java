package com.epam.webapphello.command;

import com.epam.webapphello.dao.DaoHelperFactory;
import com.epam.webapphello.service.UserService;
import com.epam.webapphello.service.UserServiceImpl;

public class CommandFactory {

   public Command createCommand(String command) {
        switch(command){
            case "login":
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            default:
                throw new IllegalArgumentException("Unknown command" + command);
        }

    }
}
