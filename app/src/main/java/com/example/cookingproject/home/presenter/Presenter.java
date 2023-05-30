package com.example.cookingproject.home.presenter;

import android.util.Log;

import com.example.cookingproject.Model.Ingredient;
import com.example.cookingproject.Model.IngredientList;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.home.view.HomeViewInterface;

import java.util.List;

public class Presenter implements NetworkDelegate {
    HomeViewInterface  homeViewInterface;
    Repository repository;

    public Presenter(HomeViewInterface homeViewInterface, Repository repository) {
        this.homeViewInterface = homeViewInterface;
        this.repository = repository;
    }
    public void getDailyMeals(){
        repository.repoDailyInspirationMeals(this);
    }
    public void addToFav(Meal meal){
        if(!meal.isFavorite()) {
            meal.setFavorite(true);
            repository.repoInsertToFav(meal);
        }

    }
    public void getAllCategory(){
        repository.repoListAllByCategory(this);
    }
    public void getAllCountry(){repository.repoListAllByCountry(this);};

    @Override
    public void onSuccessResponse(MealList mealList) {
        List<Meal> meal= mealList.getMeals();
        if(meal.get(0).getStrMeal() != null) {
            homeViewInterface.showDaily(meal);
        }
        else if(meal.get(0).getStrArea() == null) {
            homeViewInterface.showAllCategory(meal);
        }
        else if(meal.get(0).getStrCategory() == null){
            homeViewInterface.showAllCountry(meal);
        }
    }

    @Override
    public void onSuccessResponseIngredient(IngredientList ingredientList) {

    }

    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);

    }
}
