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
    private static final String SEARCH_PRODUCT_PARAMETER = "search-product";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorSearchProductMessage";
    private static final String ERROR_MESSAGE = "Product is not found";
    private static final String MEDICINE_STORAGE_ATTRIBUTE = "medicinesStorage";
    private static final String MEDICINE_STORAGE_PAGE = "/WEB-INF/view/medicineStorage.jsp";

    public SearchProductInStorageCommand(MedicineServiceImpl medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String searchingProduct = req.getParameter(SEARCH_PRODUCT_PARAMETER);
        List<Medicine> foundProduct = null;
        foundProduct = medicineService.findProductByName(searchingProduct);
        if (foundProduct.isEmpty()){
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
        }
        req.getSession().setAttribute(MEDICINE_STORAGE_ATTRIBUTE, foundProduct);
        CommandResult result = CommandResult.forward(MEDICINE_STORAGE_PAGE);
        return result;
    }
}
