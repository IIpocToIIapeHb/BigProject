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

    public SaveMedicineChangesCommand(MedicineService medicineService) {
        this.medicineService = medicineService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String medicineId = req.getParameter("medicine-id");
        String medicineName = req.getParameter("medicine-name");
        String medicineCategory = req.getParameter("medicine-category");
        String medicineDosage = req.getParameter("medicine-dosage");
        String medicinePrescriptionAvailability = req.getParameter("medicine-prescription-availability");
        String medicineForm = req.getParameter("medicine-form");
        String medicineNumber = req.getParameter("medicine-number");
        String medicinePackageAmount = req.getParameter("medicine-package-amount");
        String medicinePrice = req.getParameter("medicine-price");


        CommandResult result;
        if (!medicineName.isEmpty() && !medicineDosage.isEmpty() && !medicineNumber.isEmpty() && !medicinePackageAmount.isEmpty()
                && !medicinePrice.isEmpty()) {
            String fileName =  saveImage(req,"medicine-image");
            boolean medicinePrescriptionStatus= medicinePrescriptionAvailability.equals("yes");

            Medicine medicine = new Medicine(Long.parseLong(medicineId),medicineName,Integer.parseInt(medicineDosage),medicinePrescriptionStatus,
                    medicineForm, Integer.parseInt(medicineNumber),Integer.parseInt(medicinePackageAmount),
                    Double.parseDouble(medicinePrice),fileName);


            medicineService.save(medicine, medicineCategory);
            result = CommandResult.redirect("controller?command=showSuccessfulChangingMedicinePage");
        } else {
            req.setAttribute("errorMessage", "Invalid credentials");
            result = CommandResult.forward("/WEB-INF/view/changeMedicine.jsp");
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
