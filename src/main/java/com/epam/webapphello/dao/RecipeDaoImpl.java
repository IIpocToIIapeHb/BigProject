package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.RecipeRowMapper;
import com.epam.webapphello.mapper.RowMapper;
import com.epam.webapphello.mapper.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RecipeDaoImpl extends AbstractDao<Recipe> implements RecipeDao {

    private static final String FIND_BY_USER_AND_MEDICINE = "select * from recipe where user_id = ? and medicine_id = ?";



    public RecipeDaoImpl(Connection connection) {
        super(connection, new RecipeRowMapper());
    }

    @Override
    public Optional<Recipe> findRecipeByUserAndMedicine(Long userId, Long medicineId) throws DAOException {
        return executeForSingleResult(FIND_BY_USER_AND_MEDICINE,
                userId,
                medicineId);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected Map<String, Object> getFields(Recipe item) {
        return null;
    }

    @Override
    public Optional<Recipe> getById(Long id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DAOException {

    }



}


