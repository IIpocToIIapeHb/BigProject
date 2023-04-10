package com.epam.webapphello.command;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PrescriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class GetPrescriptionCommand implements Command {

    private final PrescriptionService prescriptionService;

    private static final String MEDICINE_ID_PARAMETER = "medicine-id";
    private static final String MEDICINE_REQUIRED_AMOUNT_PARAMETER = "medicine-required-amount";
    private static final String MEDICINE_PRESCRIPTION_STATUS_PARAMETER = "medicine-recipe-status";
    private static final String MEDICINE_PRESCRIPTION_ID_PARAMETER = "medicine-recipe-id";
    private static final String USER_ATTRIBUTE = "user";
    private static final String SHOW_CART_PAGE_COMMAND = "controller?command=showCart";

    public GetPrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicineId = req.getParameter(MEDICINE_ID_PARAMETER);
        String medicineRequiredAmount = req.getParameter(MEDICINE_REQUIRED_AMOUNT_PARAMETER);
        String medicinePrescriptionStatus = req.getParameter(MEDICINE_PRESCRIPTION_STATUS_PARAMETER);
        String medicinePrescriptionId = req.getParameter(MEDICINE_PRESCRIPTION_ID_PARAMETER);
        User user = (User)req.getSession().getAttribute(USER_ATTRIBUTE);

        prescriptionService.changePrescriptionStatus(parseLong(medicinePrescriptionId),medicinePrescriptionStatus);

        CommandResult result = CommandResult.redirect(SHOW_CART_PAGE_COMMAND);
        return result;
    }
}
