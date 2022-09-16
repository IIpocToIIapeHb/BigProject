package com.epam.webapphello.command;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PositionInfoService;
import com.epam.webapphello.service.PositionInfoServiceImpl;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

public class ShowCartCommand implements Command {

    private final PositionInfoService positionInfoService;
    private static final String USER_ATTRIBUTE = "user";
    private static final String NOT_PAID_ORDER_STATUS = "not_paid";
    private static final String POSITIONS_ATTRIBUTE = "positions";
    private static final String TOTAL_PRICE_ATTRIBUTE = "totalPrice";
    private static final String CART_PAGE_PATH = "/WEB-INF/view/cart.jsp";

    public ShowCartCommand(PositionInfoService positionInfoService) {
        this.positionInfoService = positionInfoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        User user = (User)req.getSession().getAttribute("user");
        Long userId = user.getId();

        String orderStatus = "not_paid";

        List<PositionInfo> positions = null;
        positions = positionInfoService.getPositions(userId, orderStatus);
        req.getSession().setAttribute("positions", positions);

        BigDecimal totalPrice =positionInfoService.calcTotalPrice();
        req.getSession().setAttribute("totalPrice", totalPrice);

        CommandResult result = CommandResult.forward("/WEB-INF/view/cart.jsp");
        return result;
    }
}

