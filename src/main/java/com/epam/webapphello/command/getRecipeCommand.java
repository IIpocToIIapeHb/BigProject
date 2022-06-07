package com.epam.webapphello.command;

import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.RecipeService;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class getRecipeCommand implements Command {

    private final RecipeService recipeService;

    public getRecipeCommand(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicineId = req.getParameter("medicine-id");
        String medicineRequiredAmount = req.getParameter("medicine-required-amount");
        User user = (User)req.getSession().getAttribute("user");


        Recipe recipe = recipeService.getRecipe(user.getId(), parseLong(medicineId));

        req.getSession().setAttribute("recipe", recipe);

        CommandResult result = CommandResult.redirect("controller?command=showCart");;
        return result;
    }
}
