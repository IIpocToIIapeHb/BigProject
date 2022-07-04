package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.RecipeRowMapper;

import java.sql.Connection;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class PrescriptionDaoImpl extends AbstractDao<Recipe> implements PrescriptionDao {

    private static final String FIND_BY_USER_AND_MEDICINE = "select * from recipe where user_id = ? and medicine_id = ?";
    private static final String FIND_BY_USER_AND_MEDICINE_AND_STATUS = "select * from recipe where user_id = ? and medicine_id = ? and status != ?";
    private static final String REQUEST_RECIPE_BY_USER = "INSERT INTO pharmacy.recipe (`user_id`, `medicine_id`, `status`) VALUES (?, ?, ?);";
    private static final String CHANGE_RECIPE_STATUS = "UPDATE recipe SET status = ? WHERE (id = ?);";
    private static final String SAVE_EMPTY_RECIPE = "INSERT INTO pharmacy.recipe (`user_id`, `medicine_id`) VALUES (?, ?);";
    private static final String STATUS_REQUEST_RECIPE = "pending approval";
    private static final String STATUS_APPROVED_PRESCRIPTION = "approved";
    private static final String STATUS_DECLINED_PRESCRIPTION = "declined";
    private static final String CONFIRM_PRESCRIPTION = "UPDATE pharmacy.recipe SET valid_until = ?, status = ?, amount = ? WHERE id = ?";
    private static final String EXTEND_PRESCRIPTION = "UPDATE pharmacy.recipe SET valid_until = ?, status = ? WHERE id = ?";



    public PrescriptionDaoImpl(Connection connection) {
        super(connection, new RecipeRowMapper());
    }

    @Override
    public Optional<Recipe> findRecipeByUserAndMedicine(Long userId, Long medicineId) throws DAOException {
        return executeForSingleResult(FIND_BY_USER_AND_MEDICINE,
                userId,
               medicineId);
    }

    @Override
    public boolean requestRecipeByUserAndMedicine(Long userId, Long medicineId) throws DAOException {
        return executeUpdate(REQUEST_RECIPE_BY_USER,
                userId,
                medicineId,
                STATUS_REQUEST_RECIPE);
    }

    @Override
    public boolean changeStatus(Long recipeId, String newRecipeStatus) throws DAOException {
        return executeUpdate(CHANGE_RECIPE_STATUS,
                newRecipeStatus,
                recipeId);
    }

    @Override
    public Optional<Recipe> findRecipeByUserAndMedicineAndUnwantedStatus(Long userId, Long medicineId, String recipeStatus) throws DAOException {
        return executeForSingleResult(FIND_BY_USER_AND_MEDICINE_AND_STATUS,
                userId,
                medicineId,
                recipeStatus);
    }

    @Override
    public boolean saveEmptyRecipe(Long userId, Long medicineId) throws DAOException {
       boolean result =  executeUpdate(SAVE_EMPTY_RECIPE,
                userId,
                medicineId);
       return result;
    }

    @Override
    protected String getTableName() {
        return Recipe.TABLE;
    }

    @Override
    protected Map<String, Object> getFields(Recipe item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(item.AMOUNT, item.getAmount());
        fields.put(item.STATUS, item.getStatus());
        return fields;
    }

    public Recipe getRecipeById(Long id) throws DAOException{
            String request ="select * from recipe where id = ?;";
            Recipe entity =  executeForSingleResult(request, id).get();
            return  entity;
        }

    @Override
    public void confirmPrescription(long prescriptionId, int prescriptionMedicineAmount, Date prescriptionValidUntil) throws DAOException {
         executeUpdate(CONFIRM_PRESCRIPTION,
                 prescriptionValidUntil,
                 STATUS_APPROVED_PRESCRIPTION,
                 prescriptionMedicineAmount,
                 prescriptionId );

    }

    @Override
    public void extendPrescription(long prescriptionId, Date prescriptionValidUntil) throws DAOException {
        executeUpdate(EXTEND_PRESCRIPTION,
                prescriptionValidUntil,
                STATUS_APPROVED_PRESCRIPTION,
                prescriptionId);
    }


}


