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


import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class CreateCartCommand implements Command {


    private final OrderService orderService;
    private final OrderMedicineService orderMedicineService;

    public CreateCartCommand(OrderService orderService,OrderMedicineService orderMedicineService) {
        this.orderService = orderService;
        this.orderMedicineService = orderMedicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicine_id = req.getParameter("medicine-id");
        String medicine_number = req.getParameter("medicine-number");

        User user = (User)req.getSession().getAttribute("user");

        Optional<Order> order = null;
        order = orderService.findOrderByStatusAndUser("not_paid",user.getId());

        if (order.isPresent()) {
            OrderMedicine orderMedicine = new OrderMedicine(parseLong(medicine_id),parseInt(medicine_number),order.get().getId());
            orderMedicineService.save(orderMedicine);
        } else {
            Order newOrder = new Order(user.getId(),new Date(System.currentTimeMillis()), "not_paid");
            orderService.save(newOrder,parseLong(medicine_id),parseInt(medicine_number));
        }
        CommandResult result = CommandResult.forward("/WEB-INF/view/catalog.jsp");
        return result;
    }
}
