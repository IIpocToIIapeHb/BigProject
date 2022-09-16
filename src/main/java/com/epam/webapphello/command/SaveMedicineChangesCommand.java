package com.epam.webapphello.command;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.exception.ServiceException;
import com.epam.webapphello.service.MedicineService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class SaveMedicineChangesCommand implements Command {

    public static final int BUFFER_SIZE = 1024;
    private final MedicineService medicineService;
    private static final String MEDICINE_ID_PARAMETER = "medicine-id";
    private static final String MEDICINE_NAME_PARAMETER = "medicine-name";
    private static final String MEDICINE_CATEGORY_PARAMETER = "medicine-category";
    private static final String MEDICINE_DOSAGE_PARAMETER = "medicine-dosage";
    private static final String MEDICINE_PRESCRIPTION_AVAILABILITY_PARAMETER = "medicine-prescription-availability";
    private static final String MEDICINE_FORM_PARAMETER = "medicine-form";
    private static final String MEDICINE_NUMBER_PARAMETER = "medicine-number";
    private static final String MEDICINE_PACKAGE_AMOUNT_PARAMETER = "medicine-package-amount";
    private static final String MEDICINE_PRICE_PARAMETER = "medicine-price";
    private static final String MEDICINE_IMAGE_PARAMETER = "medicine-image";
    private static final String SHOW_SUCCESSFUL_CHANGING_MEDICINE_PAGE_COMMAND = "controller?command=showSuccessfulChangingMedicinePage";
    private static final String CHANGE_MEDICINE_PAGE = "/WEB-INF/view/changeMedicine.jsp";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String ERROR_MESSAGE = "Invalid credentials";



    public SaveMedicineChangesCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicineId = req.getParameter(MEDICINE_ID_PARAMETER);
        String medicineName = req.getParameter(MEDICINE_NAME_PARAMETER);
        String medicineCategory = req.getParameter(MEDICINE_CATEGORY_PARAMETER);
        String medicineDosage = req.getParameter(MEDICINE_DOSAGE_PARAMETER);
        String medicinePrescriptionAvailability = req.getParameter(MEDICINE_PRESCRIPTION_AVAILABILITY_PARAMETER);
        String medicineForm = req.getParameter(MEDICINE_FORM_PARAMETER);
        String medicineNumber = req.getParameter(MEDICINE_NUMBER_PARAMETER);
        String medicinePackageAmount = req.getParameter(MEDICINE_PACKAGE_AMOUNT_PARAMETER);
        String medicinePrice = req.getParameter(MEDICINE_PRICE_PARAMETER);

        CommandResult result;
        if (!medicineName.isEmpty() && !medicineDosage.isEmpty() && !medicineNumber.isEmpty() && !medicinePackageAmount.isEmpty()
                && !medicinePrice.isEmpty()) {
            String fileName =  saveImage(req,MEDICINE_IMAGE_PARAMETER);
            boolean medicinePrescriptionStatus= medicinePrescriptionAvailability.equals("yes");
            Medicine medicine = new Medicine(Long.parseLong(medicineId),medicineName,Integer.parseInt(medicineDosage),medicinePrescriptionStatus,
                    medicineForm, Integer.parseInt(medicineNumber),Integer.parseInt(medicinePackageAmount),
                    Double.parseDouble(medicinePrice),fileName);
            medicineService.save(medicine, medicineCategory);
            result = CommandResult.redirect(SHOW_SUCCESSFUL_CHANGING_MEDICINE_PAGE_COMMAND);
        } else {
            req.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE);
            result = CommandResult.forward(CHANGE_MEDICINE_PAGE);
        }
        return result;
    }



    private String saveImage(HttpServletRequest req,String imageParameter) throws ServiceException {
        Part  image = null;
        InputStream fileContent=null;
        FileOutputStream fileOutputStream = null;
        String fileName=null;
        try {
            image = req.getPart(imageParameter);
             fileName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
           //  fileName = image.getName();
            fileContent = image.getInputStream();

            int length;
            byte[] bytes = new byte[BUFFER_SIZE];

            fileOutputStream = new FileOutputStream(req.getServletContext().getRealPath("")+"/static/img/storage/"+fileName);
            while ((length = fileContent.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }

        } catch (IOException  | ServletException e) {
            throw new ServiceException(e);
        } finally{
            try {
                fileContent.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
