package com.epam.webapphello.command;

import com.epam.webapphello.entity.OrderInfo;
import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.OrderInfoService;
import com.epam.webapphello.service.PrescriptionInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllOrdersPageCommand implements Command {

    private final OrderInfoService orderInfoService;
    private static final String ORDERSINFO_ATTRIBUTE = "ordersInfo";
    private static final String ALL_ORDERS_PAGE_PATH = "/WEB-INF/view/allOrdersPage.jsp";

    public ShowAllOrdersPageCommand(OrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        List<OrderInfo> ordersInfo = null;
        ordersInfo = orderInfoService.getAllPaidOrders();
        req.setAttribute(ORDERSINFO_ATTRIBUTE, ordersInfo);
        CommandResult result = CommandResult.forward(ALL_ORDERS_PAGE_PATH);
        return result;
    }
}
