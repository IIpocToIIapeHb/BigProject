package com.epam.webapphello.service;

import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.exception.ServiceException;

import java.util.Optional;

public interface RecipeService {
//    Recipe getRecipe(Long userId, Long medicineId) throws ServiceException;
     void requestRecipe(Long userId, Long medicineId) throws ServiceException;
     void changeRecipeStatus( Long recipeId, String recipeStatus) throws ServiceException;
}
