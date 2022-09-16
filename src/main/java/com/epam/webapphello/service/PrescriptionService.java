package com.epam.webapphello.service;

import com.epam.webapphello.exception.ServiceException;

public interface PrescriptionService {

     void requestPrescription(Long userId, Long medicineId) throws ServiceException;
     void changePrescriptionStatus(Long recipeId, String recipeStatus) throws ServiceException;
     void confirmPrescription(long prescriptionId, int prescriptionMedicineAmount, int prescriptionTerm) throws ServiceException;
    void extendPrescription(long prescriptionId, int prescriptionTerm) throws ServiceException;
    void changePrescriptionStatusOn(long prescriptionId, String newPrescriptionStatus) throws ServiceException;
}
