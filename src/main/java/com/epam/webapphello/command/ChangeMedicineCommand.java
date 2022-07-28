package com.epam.webapphello.command;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.MedicineService;
import com.epam.webapphello.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ChangeMedicineCommand implements Command {

    private final MedicineService medicineService;

    public ChangeMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicineId = req.getParameter("medicine-id");
        Medicine medicine = medicineService.findMedicineById(Long.parseLong(medicineId));
        req.setAttribute("changingMedicine", medicine);



        CommandResult result;


            result = CommandResult.forward("/WEB-INF/view/changeMedicine.jsp");

        return result;
    }
}
