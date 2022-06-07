package com.epam.webapphello.service;

import com.epam.webapphello.dao.DaoHelper;
import com.epam.webapphello.dao.DaoHelperFactory;
import com.epam.webapphello.dao.RecipeDao;
import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.util.Optional;

public class RecipeServiceImpl implements RecipeService {

    private DaoHelperFactory daoHelperFactory;

    public RecipeServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }



    @Override
    public Recipe getRecipe(Long userId, Long medicineId) throws ServiceException {

        Optional<Recipe> recipe = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            RecipeDao recipeDao = helper.createRecipeDao();
            recipe = recipeDao.findRecipeByUserAndMedicine(userId, medicineId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return recipe.get();
    }
}
