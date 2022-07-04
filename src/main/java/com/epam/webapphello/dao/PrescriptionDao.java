package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.exception.DAOException;

import java.sql.Date;
import java.util.Optional;

public interface PrescriptionDao extends Dao<Recipe> {
    Optional<Recipe> findRecipeByUserAndMedicine(final Long userId, final Long medicineId) throws DAOException;
    boolean requestRecipeByUserAndMedicine(final Long userId, final Long medicineId) throws DAOException;

    boolean changeStatus(Long recipeId, String newRecipeStatus) throws DAOException;

    Optional<Recipe> findRecipeByUserAndMedicineAndUnwantedStatus(Long userId, Long medicine_id, String recipeStatus) throws DAOException;

    boolean saveEmptyRecipe(Long userId, Long medicineId) throws DAOException;

    Recipe getRecipeById(Long recipeId) throws DAOException;

    void confirmPrescription(long prescriptionId, int prescriptionMedicineAmount, Date prescriptionValidUntil) throws DAOException;
}
