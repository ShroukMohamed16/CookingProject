package com.example.cookingproject.meals.presenter;

import com.example.cookingproject.Model.Ingredient;
import com.example.cookingproject.Model.IngredientList;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.meals.view.mealViewInterface;

import java.util.List;

public class MealPresenter implements NetworkDelegate {
    mealViewInterface MealInterface;
    Repository repository;

    public MealPresenter(mealViewInterface MealInterface, Repository repository) {
         this.MealInterface = MealInterface;
         this.repository = repository;
    }



    public void addToPlan(Meal meal) {
    }

    public void addToFavorite(Meal meal) {
        repository.repoInsertToFav(meal);
    }

    public void getMealItem(String mealNameItem){
        repository.repoSearchByName(this , mealNameItem);
    }

    @Override
    public void onSuccessResponse(MealList meals) {
        List<Meal> meal = meals.getMeals();
        MealInterface.showMeal(meal);
    }

    @Override
    public void onSuccessResponseIngredient(IngredientList ingredientList) {

    }

    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);
    }
}
