package com.epam.webapphello.command;

import com.epam.webapphello.entity.*;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.OrderInfoService;
import com.epam.webapphello.service.OrderMedicineService;
import com.epam.webapphello.service.OrderService;
import com.epam.webapphello.service.PositionInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class OrderDetailsCommand implements Command {


    private final OrderInfoService orderInfoService;
    private final PositionInfoService positionInfoService;

    public OrderDetailsCommand(OrderInfoService orderInfoService, PositionInfoService positionInfoService) {
        this.orderInfoService = orderInfoService;
        this.positionInfoService = positionInfoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String orderInfoId = req.getParameter("order-id");


        Optional<OrderInfo> optionalOrderInfo = orderInfoService.findOrderInfoById(Long.parseLong(orderInfoId));

        CommandResult result=null;
        if (optionalOrderInfo.isPresent()) {
            OrderInfo orderInfo = optionalOrderInfo.get();
            req.setAttribute("orderInfo", orderInfo);


            List<PositionInfo> positions = null;
            positions = positionInfoService.getPositions(orderInfo.getId());
            req.setAttribute("positions", positions);

            BigDecimal totalPrice =positionInfoService.calcTotalPrice();
            req.setAttribute("totalPrice", totalPrice);

            result = CommandResult.forward("/WEB-INF/view/orderDetails.jsp");
        } else {

            result = CommandResult.forward("/WEB-INF/view/catalog.jsp");
        }
        return result;
    }
}
