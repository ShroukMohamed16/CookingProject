package com.example.cookingproject.categories.presenter;

import com.example.cookingproject.Model.Ingredient;
import com.example.cookingproject.Model.IngredientList;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.categories.view.CategoryViewInterface;

import java.util.List;

public class CategoryPresenter implements NetworkDelegate {

    CategoryViewInterface categoryViewInterface;
    Repository repository;

    public CategoryPresenter(CategoryViewInterface categoryViewInterface, Repository repository) {
        this.categoryViewInterface = categoryViewInterface;
        this.repository = repository;
    }
    public void mealsOfCategory(String CategoryName){
        repository.repoFilterByCategory(this,CategoryName);
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
        categoryViewInterface.showAllMealsOfCategory(meal);

    }

    @Override
    public void onSuccessResponseIngredient(IngredientList ingredientList) {

    }

    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);
    }
}
