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


import static java.lang.Byte.parseByte;
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
        String medicineId = req.getParameter("medicine-id");
        String medicineNumber = req.getParameter("medicine-number");
        String medicineWithRecipe =  req.getParameter("medicine-with-recipe");

        User user = (User)req.getSession().getAttribute("user");

        Optional<Order> order = null;
        order = orderService.findOrderByStatusAndUser("not_paid",user.getId());


        if (order.isPresent()) {
            Optional<OrderMedicine> orderMedicine = null;
            orderMedicine = orderMedicineService.findOrderMedicine(order.get().getId(),parseLong(medicineId));
                if (orderMedicine.isPresent()) {
                    orderMedicineService.addMedicineOrderAmount(orderMedicine.get().getId(),orderMedicine.get().getRequired_amount(),parseInt(medicineNumber));
                } else {
                    OrderMedicine newOrderMedicine = new OrderMedicine(parseLong(medicineId), parseInt(medicineNumber), order.get().getId());
                    orderMedicineService.save(newOrderMedicine, parseByte(medicineWithRecipe), user.getId());
                }
        } else {
            Order newOrder = new Order(user.getId(),new Date(System.currentTimeMillis()), "not_paid");
            orderService.save(newOrder,parseLong(medicineId),parseInt(medicineNumber), user.getId(),parseByte(medicineWithRecipe));

        }
        CommandResult result = CommandResult.forward("/WEB-INF/view/catalog.jsp");
        return result;
    }
}
