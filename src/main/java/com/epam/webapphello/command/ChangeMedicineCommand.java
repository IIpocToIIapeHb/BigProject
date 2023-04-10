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

    private static final String MEDICINE_ID_PARAMETER = "medicine-id";
    private static final String CHANGING_MEDICINE_ATTRIBUTE = "changingMedicine";
    private static final String CHANGE_MEDICINE_PAGE_PATH = "/WEB-INF/view/changeMedicine.jsp";

    public ChangeMedicineCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicineId = req.getParameter(MEDICINE_ID_PARAMETER);
        Medicine medicine = medicineService.findMedicineById(Long.parseLong(medicineId));
        req.setAttribute(CHANGING_MEDICINE_ATTRIBUTE, medicine);

        CommandResult result;
        result = CommandResult.forward(CHANGE_MEDICINE_PAGE_PATH);
        return result;
    }
}
