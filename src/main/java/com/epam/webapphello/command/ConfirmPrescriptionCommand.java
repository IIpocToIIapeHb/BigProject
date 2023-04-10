package com.epam.webapphello.command;

import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PrescriptionService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmPrescriptionCommand implements Command {

    private final PrescriptionService prescriptionService;

    private static final String PRESCRIPTION_ID_PARAMETER = "prescription-id";
    private static final String PRESCRIPTION_MEDICINE_AMOUNT_PARAMETER = "prescription-medicine-amount";
    private static final String PRESCRIPTION_TERM_PARAMETER = "prescription-term";
    private static final String PRESCRIPTION_MEDICINE_AMOUNT_REGEX = "[\\d^0)]+";
    private static final String PRESCRIPTION_TERM_REGEX = "[1-6]{1}[0-9]{1}";
    private static final String CONFIRMATION_REQUESTS_PAGE_COMMAND = "controller?command=ConfirmationRequestsPage";
    private static final String CONFIRMATION_REQUESTS_PAGE_PATH = "/WEB-INF/view/confirmationRequestsPage.jsp";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "doctorErrorMessage";
    private static final String ERROR_MESSAGE= "Invalid date";

    public ConfirmPrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String prescriptionId = req.getParameter(PRESCRIPTION_ID_PARAMETER);
        String prescriptionMedicineAmount = req.getParameter(PRESCRIPTION_MEDICINE_AMOUNT_PARAMETER);
        String prescriptionTerm = req.getParameter(PRESCRIPTION_TERM_PARAMETER);

        CommandResult result=null;

        if (!prescriptionMedicineAmount.isEmpty() && prescriptionMedicineAmount.matches(PRESCRIPTION_MEDICINE_AMOUNT_REGEX )
            && !prescriptionTerm.isEmpty() && prescriptionTerm.matches(PRESCRIPTION_TERM_REGEX)) {
            prescriptionService.confirmPrescription(Long.parseLong(prescriptionId), Integer.parseInt(prescriptionMedicineAmount),
                    Integer.parseInt(prescriptionTerm));
            result = CommandResult.redirect(CONFIRMATION_REQUESTS_PAGE_COMMAND);
        } else {
            PrescriptionInfo.COUNTER=0;
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            result = CommandResult.forward(CONFIRMATION_REQUESTS_PAGE_PATH);
        }
        return result;
    }
}
