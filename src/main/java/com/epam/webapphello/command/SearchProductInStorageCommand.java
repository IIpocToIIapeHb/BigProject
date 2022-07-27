package com.epam.webapphello.command;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.MedicineService;
import com.epam.webapphello.service.MedicineServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchProductInStorageCommand implements Command {

    private final MedicineService medicineService;

    public SearchProductInStorageCommand(MedicineServiceImpl medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String searchingProduct = req.getParameter("search-product");
        List<Medicine> foundProduct = null;
        foundProduct = medicineService.findProductByName(searchingProduct);
        if (foundProduct.isEmpty()){
           // req.getSession().setAttribute("errorSearchProductMessage", "Product is not found");
            req.setAttribute("errorSearchProductMessage", "Product is not found");
        }

        req.setAttribute("medicinesStorage", foundProduct);
        CommandResult result = CommandResult.forward("/WEB-INF/view/medicineStorage.jsp");
        return result;
    }
}
