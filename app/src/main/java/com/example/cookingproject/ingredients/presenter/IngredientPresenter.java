package com.example.cookingproject.ingredients.presenter;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.ingredients.view.IngredientInterface;

import java.util.List;

public class IngredientPresenter implements NetworkDelegate {
    IngredientInterface view;
    Repository repository;

    public IngredientPresenter(IngredientInterface view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }
    public void getMealsByIngredient(String IngredientName){
        repository.repoFilterByIngredient(this,IngredientName);
    }
    public void addToFav(Meal meal){
        if(!meal.isFavorite()) {
            meal.setFavorite(true);
            repository.repoInsertToFav(meal);
        }

    }

    @Override
    public void onSuccessResponse(MealList meals) {
        List<Meal> meal = meals.getMeals();
        view.showAllMealsOfIngredient(meal);

    }

    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);


    }
}
