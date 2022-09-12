package com.epam.webapphello.command;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.OrderMedicineService;
import com.epam.webapphello.service.OrderService;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Optional;


import static java.lang.Boolean.parseBoolean;
import static java.lang.Byte.parseByte;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class CreateCartCommand implements Command {

    private final OrderService orderService;
    private final OrderMedicineService orderMedicineService;

    private static final String MEDICINE_ID_PARAMETER = "medicine-id";
    private static final String MEDICINE_NUMBER_PARAMETER = "medicine-number";
    private static final String MEDICINE_PRESCRIPTION_PARAMETER = "medicine-with-recipe";
    private static final String USER_ATTRIBUTE = "user";
    private static final String NOT_PAID_ORDER_STATUS = "not_paid";
    private static final String CATALOG_PAGE_PATH = "/WEB-INF/view/catalog.jsp";

    public CreateCartCommand(OrderService orderService,OrderMedicineService orderMedicineService) {
        this.orderService = orderService;
        this.orderMedicineService = orderMedicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicineId = req.getParameter(MEDICINE_ID_PARAMETER);
        String medicineNumber = req.getParameter(MEDICINE_NUMBER_PARAMETER);
        String medicineWithRecipe =  req.getParameter(MEDICINE_PRESCRIPTION_PARAMETER);

        User user = (User)req.getSession().getAttribute(USER_ATTRIBUTE);

        Optional<Order> order = null;
        order = orderService.findOrderByStatusAndUser(NOT_PAID_ORDER_STATUS,user.getId());

        if (order.isPresent()) {
            Optional<OrderMedicine> orderMedicine = null;
            orderMedicine = orderMedicineService.findOrderMedicine(order.get().getId(),parseLong(medicineId));
                if (orderMedicine.isPresent()) {
                    orderMedicineService.addMedicineOrderAmount(orderMedicine.get().getId(),orderMedicine.get().getRequired_amount(),Integer.parseInt(medicineNumber));
                } else {
                    OrderMedicine newOrderMedicine = new OrderMedicine(Long.parseLong(medicineId), Integer.parseInt(medicineNumber), order.get().getId());
                    orderMedicineService.save(newOrderMedicine, Boolean.parseBoolean(medicineWithRecipe), user.getId());
                }
        } else {
            Order newOrder = new Order(user.getId(),new Date(System.currentTimeMillis()), NOT_PAID_ORDER_STATUS);
            orderService.save(newOrder,parseLong(medicineId),parseInt(medicineNumber), user.getId(),Boolean.parseBoolean(medicineWithRecipe));

        }
        CommandResult result = CommandResult.forward(CATALOG_PAGE_PATH);
        return result;
    }
}
