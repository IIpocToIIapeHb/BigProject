package com.epam.webapphello.command;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.MedicineService;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class CatalogCommand implements Command {

    private final MedicineService medicineService;

    public CatalogCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        List<Medicine> medicines = null;
        medicines = medicineService.getAll();
        req.getSession().setAttribute("medicines", medicines);
        CommandResult result = CommandResult.forward("/WEB-INF/view/catalog.jsp");
        return result;
    }
}
