package com.epam.webapphello.command;

import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.PrescriptionInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllPrescriptionsPageCommand implements Command {

    private final PrescriptionInfoService prescriptionInfoService;

    public ShowAllPrescriptionsPageCommand(PrescriptionInfoService prescriptionInfoService) {
        this.prescriptionInfoService = prescriptionInfoService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        List<PrescriptionInfo> prescriptionsInfo = null;
        prescriptionsInfo = prescriptionInfoService.getAllPrescriptions();
        req.getSession().setAttribute("prescriptionsInfo", prescriptionsInfo);
        CommandResult result = CommandResult.forward("/WEB-INF/view/allPrescriptionsPage.jsp");
        return result;
    }
}
