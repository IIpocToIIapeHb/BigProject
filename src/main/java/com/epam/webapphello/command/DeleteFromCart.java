package com.epam.webapphello.command;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.OrderMedicineService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

public class DeleteFromCart implements Command {

    private final OrderMedicineService orderMedicineService;

    private static final String ORDER_MEDICINE_ID_PARAMETER = "order-medicine-id";
    private static final String PRESCRIPTION_ID_PARAMETER = "recipe-id";
    private static final String PRESCRIPTION_STATUS_PARAMETER = "recipe-status";
    private static final String SHOW_CART_PAGE_COMMAND = "controller?command=showCart";


    public DeleteFromCart(OrderMedicineService orderMedicineService) {
        this.orderMedicineService = orderMedicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String orderMedicineId = req.getParameter(ORDER_MEDICINE_ID_PARAMETER);
        String recipeId = req.getParameter(PRESCRIPTION_ID_PARAMETER);
        String recipeStatus = req.getParameter(PRESCRIPTION_STATUS_PARAMETER);

        orderMedicineService.removeMedicine(parseLong(orderMedicineId),parseLong(recipeId),recipeStatus);

        CommandResult result = CommandResult.redirect(SHOW_CART_PAGE_COMMAND);
        return result;
    }
}
