package com.epam.webapphello.command;

import com.epam.webapphello.dao.DaoHelperFactory;
import com.epam.webapphello.service.*;

public class CommandFactory {

   public Command createCommand(String command) {
        switch(command){
            case "login":
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case "mainPage":
                return new ShowPageCommand("/WEB-INF/view/main.jsp");
            case "catalog":
                return new CatalogCommand(new MedicineServiceImpl(new DaoHelperFactory()));
            case "searchProduct":
                return new SearchProductCommand(new MedicineServiceImpl(new DaoHelperFactory()));
            case "cart":
                return new CreateCartCommand(new OrderServiceImpl(new DaoHelperFactory()),new OrderMedicineServiceImpl(new DaoHelperFactory()));
            case "showCart":
                return new ShowCartCommand(new PositionInfoServiceImpl(new DaoHelperFactory()));
            case "getRecipe":
                return new GetRecipeCommand(new PrescriptionServiceImpl(new DaoHelperFactory()));
            case "deleteFromCart":
                return new DeleteFromCart(new OrderMedicineServiceImpl(new DaoHelperFactory()));
            case "payOrder":
                return new OrderPayCommand(new PositionInfoServiceImpl(new DaoHelperFactory()));
            case "payedPage":
                return new ShowPageCommand("/WEB-INF/view/payedPage.jsp");
            case "ConfirmationRequestsPage":
                return new ConfirmationRequestsPageCommand(new PrescriptionInfoServiceImpl(new DaoHelperFactory()));
            case "changeLang":
                return new ChangeLang();
            case "confirmPrescription":
                return new ConfirmPrescriptionCommand(new PrescriptionServiceImpl(new DaoHelperFactory()));
            default:
                throw new IllegalArgumentException("Unknown command" + command);
        }

    }
}
