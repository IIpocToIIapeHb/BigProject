package com.epam.webapphello.command;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.MedicineService;
import com.epam.webapphello.service.PrescriptionInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConfirmationRequestsPageCommand implements Command {

    private final PrescriptionInfoService prescriptionInfoService;

    public static final String PRESCRIPTION_STATUS = "pending approval";
    public static final String PRESCRIPTIONS_INFO_ATTRIBUTE = "prescriptionsInfo";
    public static final String CONFIRMATION_REQUEST_PAGE_PATH = "/WEB-INF/view/confirmationRequestsPage.jsp";

    public ConfirmationRequestsPageCommand(PrescriptionInfoService prescriptionInfoService) {
        this.prescriptionInfoService = prescriptionInfoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        List<PrescriptionInfo> prescriptionsInfo = null;
        prescriptionsInfo = prescriptionInfoService.getAllByStatus(PRESCRIPTION_STATUS);
        req.getSession().setAttribute(PRESCRIPTIONS_INFO_ATTRIBUTE, prescriptionsInfo);
        CommandResult result = CommandResult.forward(CONFIRMATION_REQUEST_PAGE_PATH);
        return result;
    }
}
