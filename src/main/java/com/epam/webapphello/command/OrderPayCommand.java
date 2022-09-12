package com.epam.webapphello.command;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceErrorException;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PositionInfoService;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class OrderPayCommand implements Command {

    private final PositionInfoService positionInfoService;

    private static final String POSITIONS_ATTRIBUTE = "positions";
    private static final String USER_ATTRIBUTE = "user";
    private static final String ORDER_ATTRIBUTE = "order";
    private static final String TOTAL_PRICE_ATTRIBUTE = "totalPrice";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorPayMessage";
    private static final String ERROR_MESSAGE = "The cart is empty!";
    private static final String CART_PAGE_PATH = "/WEB-INF/view/cart.jsp";
    private static final String PAYED_PAGE_COMMAND = "controller?command=payedPage";


    public OrderPayCommand(PositionInfoService positionInfoService) {
        this.positionInfoService = positionInfoService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        List<PositionInfo> positions = (List<PositionInfo>) req.getSession().getAttribute(POSITIONS_ATTRIBUTE);
        User user = (User)req.getSession().getAttribute(USER_ATTRIBUTE);
        Order order = (Order)req.getSession().getAttribute(ORDER_ATTRIBUTE);
        BigDecimal totalPrice = (BigDecimal) req.getSession().getAttribute(TOTAL_PRICE_ATTRIBUTE);

        CommandResult result;

        if (positions.isEmpty()){
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE,ERROR_MESSAGE);
            result = CommandResult.forward(CART_PAGE_PATH);
            return result;
        }

        try {
            positionInfoService.pay(positions, user,  totalPrice);
        } catch (ServiceErrorException e) {
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
          result = CommandResult.forward(CART_PAGE_PATH);
          return result;
        }

        result =  CommandResult.redirect(PAYED_PAGE_COMMAND);
        return result;
    }
}
