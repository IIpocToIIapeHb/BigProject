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

    public OrderPayCommand(PositionInfoService positionInfoService) {
        this.positionInfoService = positionInfoService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        List<PositionInfo> positions = (List<PositionInfo>) req.getSession().getAttribute("positions");

        User user = (User)req.getSession().getAttribute("user");
        Order order = (Order)req.getSession().getAttribute("order");
        BigDecimal totalPrice = (BigDecimal) req.getSession().getAttribute("totalPrice");

        CommandResult result;

        if (positions.isEmpty()){
            req.setAttribute("errorPayMessage","The cart is empty!");
            result = CommandResult.forward("/WEB-INF/view/cart.jsp");
            return result;
        }
        try {
            positionInfoService.pay(positions, user,  totalPrice);
        } catch (ServiceErrorException e) {
            req.setAttribute("errorPayMessage", e.getMessage());
          result = CommandResult.forward("/WEB-INF/view/cart.jsp");
          return result;
        }

        result =  CommandResult.redirect("controller?command=payedPage");
        return result;
    }
}
