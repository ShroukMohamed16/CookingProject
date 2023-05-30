package com.example.cookingproject.search.presenter;

import com.example.cookingproject.Model.Ingredient;
import com.example.cookingproject.Model.IngredientList;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.home.view.HomeViewInterface;
import com.example.cookingproject.search.view.searchViewInterface;

import java.util.List;

public class SearchPresenter implements NetworkDelegate {
    searchViewInterface searchInterface;
    Repository repository;

    public SearchPresenter( searchViewInterface searchInterface, Repository repository) {
        this.searchInterface = searchInterface;
        this.repository = repository;
    }
    public void getAllIngredient(){
        repository.repoListAllByIngredient(this);
    }

    public void getAllCategory(){
        repository.repoListAllByCategory(this);
    }
    public void getAllCountry(){repository.repoListAllByCountry(this);};

    @Override
    public void onSuccessResponseIngredient(IngredientList ingredientList) {
        searchInterface.showAllIngredient(ingredientList.getIngredients());

    }
    @Override
    public void onSuccessResponse(MealList mealList) {
        List<Meal> meal= mealList.getMeals();
         if(meal.get(0).getStrArea() == null) {
            searchInterface.showAllCategory(meal);
        }
        else if(meal.get(0).getStrCategory() == null){
            searchInterface.showAllCountry(meal);
        }
    }


    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);

    }
}
