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



//    @Override
//    public Recipe getRecipe(Long userId, Long medicineId) throws ServiceException {
//
//        Optional<Recipe> recipe = null;
//        try (DaoHelper helper = daoHelperFactory.create()) {
//            RecipeDao recipeDao = helper.createRecipeDao();
//            recipe = recipeDao.findRecipeByUserAndMedicine(userId, medicineId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//        return recipe.get();
//    }

    public void requestRecipe(Long userId, Long medicineId) throws ServiceException {
        Recipe recipe;
        try (DaoHelper helper = daoHelperFactory.create()) {
            RecipeDao recipeDao = helper.createRecipeDao();
            recipeDao.requestRecipeByUserAndMedicine(userId, medicineId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void changeRecipeStatus(Long recipeId, String recipeStatus) throws ServiceException {
        String newRecipeStatus= null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            RecipeDao recipeDao = helper.createRecipeDao();
            switch (recipeStatus){
                case("pending approval"):
                case("extension requested"):
                    break;
                case ("declined"):
                    newRecipeStatus = "pending approval";
                    recipeDao.changeStatus(recipeId, newRecipeStatus);
                    break;
                case ("overdue"):
                    newRecipeStatus = "extension requested";
                    recipeDao.changeStatus(recipeId, newRecipeStatus);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown recipe status" + recipeStatus);
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
