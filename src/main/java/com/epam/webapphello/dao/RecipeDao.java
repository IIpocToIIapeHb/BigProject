package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.exception.DAOException;

import java.util.Optional;

public interface RecipeDao {
    public Optional<Recipe> findRecipeByUserAndMedicine(final Long userId, final Long medicineId) throws DAOException;
}
