package com.epam.webapphello.command;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceErrorException;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.OrderService;
import com.epam.webapphello.service.PositionInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class PerformOrderCommand implements Command {

    private final OrderService orderService;

    private static final String ORDER_ID_PARAMETER = "order-id";
    private static final String ORDER_IS_PERFORMED_PAGE_COMMAND = "controller?command=orderIsPerformedPage";

    public PerformOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String orderId = req.getParameter(ORDER_ID_PARAMETER);

        CommandResult result;
        orderService.performOrder(Long.parseLong(orderId));
        result =  CommandResult.redirect(ORDER_IS_PERFORMED_PAGE_COMMAND);
        return result;
    }
}
