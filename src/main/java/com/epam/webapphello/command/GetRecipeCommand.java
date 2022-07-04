package com.epam.webapphello.command;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PrescriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class GetRecipeCommand implements Command {

    private final PrescriptionService recipeService;

    public GetRecipeCommand(PrescriptionService recipeService) {
        this.recipeService = recipeService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicineId = req.getParameter("medicine-id");
        String medicineRequiredAmount = req.getParameter("medicine-required-amount");
        String medicineRecipeStatus = req.getParameter("medicine-recipe-status");
        String medicineRecipeId = req.getParameter("medicine-recipe-id");
        User user = (User)req.getSession().getAttribute("user");


            recipeService.changeRecipeStatus(parseLong(medicineRecipeId),medicineRecipeStatus);


        CommandResult result = CommandResult.redirect("controller?command=showCart");
        return result;
    }
}
