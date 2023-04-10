package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Prescription;
import com.epam.webapphello.exception.DAOException;

import java.sql.Date;
import java.util.Optional;

public interface PrescriptionDao extends Dao<Prescription> {
    Optional<Prescription> findRecipeByUserAndMedicine(final Long userId, final Long medicineId) throws DAOException;
    boolean requestPrescriptionByUserAndMedicine(final Long userId, final Long medicineId) throws DAOException;

    boolean changeStatus(Long recipeId, String newRecipeStatus) throws DAOException;

    Optional<Prescription> findPrescriptionByUserAndMedicineAndUnwantedStatus(Long userId, Long medicine_id, String recipeStatus) throws DAOException;

    boolean saveEmptyPrescription(Long userId, Long medicineId) throws DAOException;

    Prescription getRecipeById(Long recipeId) throws DAOException;

    void confirmPrescription(long prescriptionId, int prescriptionMedicineAmount, Date prescriptionValidUntil) throws DAOException;

    void extendPrescription(long prescriptionId, Date prescriptionValidUntil) throws DAOException;
}
