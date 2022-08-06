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

    public PerformOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String orderId = req.getParameter("order-id");

        CommandResult result;

            orderService.performOrder(Long.parseLong(orderId));


        result =  CommandResult.redirect("controller?command=orderIsPerformedPage");
        return result;
    }
}
