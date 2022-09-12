package com.epam.webapphello.command;

import com.epam.webapphello.dao.DaoHelperFactory;
import com.epam.webapphello.service.*;

public class CommandFactory {

    private static final String MAIN_PAGE_PATH = "/WEB-INF/view/main.jsp";
    private static final String PAYED_PAGE_PATH = "/WEB-INF/view/payedPage.jsp";
    private static final String ADD_MEDICINE_PAGE_PATH = "/WEB-INF/view/addMedicine.jsp";
    private static final String MEDICINE_STORAGE_PAGE_PATH = "/WEB-INF/view/medicineStorage.jsp";
    private static final String SUCCESSFUL_MEDICINE_CHANGING_PAGE_PATH = "/WEB-INF/view/successfulMedicineChanging.jsp";
    private static final String SUCCESSFUL_MEDICINE_ADDING_PAGE_PATH = "/WEB-INF/view/successfulMedicineAdding.jsp";
    private static final String ORDER_IS_PERFORMED_PAGE_PATH = "/WEB-INF/view/orderIsPerformed.jsp";
    private static final String USERS_STORAGE_PAGE_PATH = "/WEB-INF/view/usersStorage.jsp";

   public Command createCommand(String command) {
        switch(command){
            case "login":
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case "mainPage":
                return new ShowPageCommand(MAIN_PAGE_PATH);
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
                return new ShowPageCommand(PAYED_PAGE_PATH);
            case "ConfirmationRequestsPage":
                return new ConfirmationRequestsPageCommand(new PrescriptionInfoServiceImpl(new DaoHelperFactory()));
            case "extensionRequestsPage":
                return new ExtensionRequestsPageCommand(new PrescriptionInfoServiceImpl(new DaoHelperFactory()));
            case "changeLang":
                return new ChangeLang();
            case "confirmPrescription":
                return new ConfirmPrescriptionCommand(new PrescriptionServiceImpl(new DaoHelperFactory()));
            case "extendPrescription":
                return new ExtendPrescriptionCommand(new PrescriptionServiceImpl(new DaoHelperFactory()));
            case "refusePrescription":
                return new RefusePrescriptionCommand(new PrescriptionServiceImpl(new DaoHelperFactory()));
            case "allPrescriptionsPage":
                return new ShowAllPrescriptionsPageCommand(new PrescriptionInfoServiceImpl(new DaoHelperFactory()));
            case "saveMedicine":
                return new SaveMedicineCommand(new MedicineServiceImpl(new DaoHelperFactory()));
            case "addMedicinePage":
                return new ShowPageCommand(ADD_MEDICINE_PAGE_PATH);
            case "showMedicineStoragePage":
                return new ShowPageCommand(MEDICINE_STORAGE_PAGE_PATH);
            case "showSuccessfulChangingMedicinePage":
                return new ShowPageCommand(SUCCESSFUL_MEDICINE_CHANGING_PAGE_PATH);
            case "showSuccessfulAddingMedicinePage":
                return new ShowPageCommand(SUCCESSFUL_MEDICINE_ADDING_PAGE_PATH);
            case "searchProductInStorage":
                return new SearchProductInStorageCommand(new MedicineServiceImpl(new DaoHelperFactory()));
            case "changeMedicine":
                return new ChangeMedicineCommand(new MedicineServiceImpl(new DaoHelperFactory()));
            case "saveMedicineChanges":
                return new SaveMedicineChangesCommand(new MedicineServiceImpl(new DaoHelperFactory()));
            case "allOrders":
                return new ShowAllOrdersPageCommand(new OrderInfoServiceImpl(new DaoHelperFactory()));
            case "performOrderDetails":
                return new OrderDetailsCommand(new OrderInfoServiceImpl(new DaoHelperFactory()),new PositionInfoServiceImpl(new DaoHelperFactory()));
            case "performOrder":
                return new PerformOrderCommand(new OrderServiceImpl(new DaoHelperFactory()));
            case "orderIsPerformedPage":
                return new ShowPageCommand(ORDER_IS_PERFORMED_PAGE_PATH);
            case "showAdminUsersPage":
                return new ShowPageCommand(USERS_STORAGE_PAGE_PATH);
            case "searchUser":
                return new SearchUserCommand(new UserServiceImpl(new DaoHelperFactory()));
            case "changeLockStatus":
                return new ChangeLockUserStatus(new UserServiceImpl(new DaoHelperFactory()));
                default:
            throw new IllegalArgumentException("Unknown command" + command);
        }

    }
}
