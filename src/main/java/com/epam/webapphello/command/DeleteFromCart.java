package com.epam.webapphello.command;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.OrderMedicineService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

public class DeleteFromCart implements Command {

    private final OrderMedicineService orderMedicineService;

    public DeleteFromCart(OrderMedicineService orderMedicineService) {
        this.orderMedicineService = orderMedicineService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String orderMedicineId = req.getParameter("order-medicine-id");
        String recipeId = req.getParameter("recipe-id");
        String recipeStatus = req.getParameter("recipe-status");

        orderMedicineService.removeMedicine(parseLong(orderMedicineId),parseLong(recipeId),recipeStatus);

        CommandResult result = CommandResult.redirect("controller?command=showCart");
        return result;
    }
}
