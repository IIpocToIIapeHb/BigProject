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

    private static final String ORDER_ID_PARAMETER = "order-id";
    private static final String ORDER_INFO_ATTRIBUTE = "orderInfo";
    private static final String POSITIONS_ATTRIBUTE = "positions";
    private static final String TOTAL_PRICE_ATTRIBUTE = "totalPrice";
    private static final String ORDER_DETAILS_PAGE_PATH = "/WEB-INF/view/orderDetails.jsp";
    private static final String CATALOG_PAGE_PATH = "/WEB-INF/view/catalog.jsp";

    public OrderDetailsCommand(OrderInfoService orderInfoService, PositionInfoService positionInfoService) {
        this.orderInfoService = orderInfoService;
        this.positionInfoService = positionInfoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String orderInfoId = req.getParameter(ORDER_ID_PARAMETER);

        Optional<OrderInfo> optionalOrderInfo = orderInfoService.findOrderInfoById(Long.parseLong(orderInfoId));

        CommandResult result=null;
        if (optionalOrderInfo.isPresent()) {
            OrderInfo orderInfo = optionalOrderInfo.get();
            req.setAttribute(ORDER_INFO_ATTRIBUTE, orderInfo);

            List<PositionInfo> positions = null;
            positions = positionInfoService.getPositions(orderInfo.getId());
            req.setAttribute(POSITIONS_ATTRIBUTE, positions);

            BigDecimal totalPrice =positionInfoService.calcTotalPrice();
            req.setAttribute(TOTAL_PRICE_ATTRIBUTE, totalPrice);

            result = CommandResult.forward(ORDER_DETAILS_PAGE_PATH);
        } else {

            result = CommandResult.forward(CATALOG_PAGE_PATH);
        }
        return result;
    }
}
