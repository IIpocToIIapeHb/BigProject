package com.epam.webapphello.command;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceErrorException;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PositionInfoService;
import com.epam.webapphello.service.PrescriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class ConfirmPrescriptionCommand implements Command {

    private final PrescriptionService prescriptionService;

    public ConfirmPrescriptionCommand(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String prescriptionId = req.getParameter("prescription-id");
        String prescriptionMedicineAmount = req.getParameter("prescription-medicine-amount");
        String prescriptionTerm = req.getParameter("prescription-term");



        prescriptionService.confirmPrescription(Long.parseLong(prescriptionId),Integer.parseInt(prescriptionMedicineAmount),
                                 Integer.parseInt(prescriptionTerm));




        CommandResult result =  CommandResult.redirect("controller?command=ConfirmationRequestsPage");
        return result;
    }
}
