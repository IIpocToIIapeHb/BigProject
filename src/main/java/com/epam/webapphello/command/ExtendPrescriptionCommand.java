package com.epam.webapphello.command;

import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PrescriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExtendPrescriptionCommand implements Command {

    private final PrescriptionService prescriptionService;

    private static final String PRESCRIPTION_ID_PARAMETER = "prescription-id";
    private static final String PRESCRIPTION_TERM_PARAMETER = "prescription-term";
    private static final String EXTENSION_REQUEST_PAGE_COMMAND = "controller?command=extensionRequestsPage";
    private static final String EXTENSION_REQUEST_PAGE_PATH = "/WEB-INF/view/extensionRequestsPage.jsp";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "doctorErrorMessage";
    private static final String ERROR_MESSAGE= "Invalid date";


    public ExtendPrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String prescriptionId = req.getParameter(PRESCRIPTION_ID_PARAMETER);
        String prescriptionTerm = req.getParameter(PRESCRIPTION_TERM_PARAMETER);

        CommandResult result=null;
        int periodInDays = Integer.parseInt(prescriptionTerm);
        if (!prescriptionTerm.isEmpty() && periodInDays<=90) {
            prescriptionService.extendPrescription(Long.parseLong(prescriptionId),periodInDays);
            result = CommandResult.redirect(EXTENSION_REQUEST_PAGE_COMMAND);
        } else {
            PrescriptionInfo.COUNTER=0;
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            result = CommandResult.forward(EXTENSION_REQUEST_PAGE_PATH);
        }
        return result;
    }
}
