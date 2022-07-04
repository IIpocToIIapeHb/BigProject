package com.epam.webapphello.command;

import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PrescriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExtendPrescriptionCommand implements Command {

    private final PrescriptionService prescriptionService;

    public ExtendPrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String prescriptionId = req.getParameter("prescription-id");
        String prescriptionTerm = req.getParameter("prescription-term");

        CommandResult result=null;

        if (!prescriptionTerm.isEmpty() && prescriptionTerm.matches("[1-60]")) {
            prescriptionService.extendPrescription(Long.parseLong(prescriptionId), Integer.parseInt(prescriptionTerm));
            result = CommandResult.redirect("controller?command=extensionRequestsPage");
        } else {
            PrescriptionInfo.COUNTER=0;
            req.setAttribute("doctorErrorMessage", "Invalid date");
            result = CommandResult.forward("/WEB-INF/view/extensionRequestsPage.jsp");
        }
        return result;
    }
}
