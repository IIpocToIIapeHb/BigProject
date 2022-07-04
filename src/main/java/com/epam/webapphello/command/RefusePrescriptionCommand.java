package com.epam.webapphello.command;

import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PrescriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefusePrescriptionCommand implements Command {

    private final PrescriptionService prescriptionService;
    private static final String REFUSE_PRESCRIPTION_STATUS = "declined";

    public RefusePrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String prescriptionId = req.getParameter("prescription-id");

        prescriptionService.changePrescriptionStatusOn(Long.parseLong(prescriptionId),REFUSE_PRESCRIPTION_STATUS );
        CommandResult   result = CommandResult.redirect("controller?command=extensionRequestsPage");


        return result;
    }
}
