package com.example.cookingproject.countries.presenter;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.countries.view.CountryViewInterface;

import java.util.List;

public class CountryPresenter implements NetworkDelegate {

    CountryViewInterface countryViewInterface;
    Repository repository;

    public CountryPresenter(CountryViewInterface countryViewInterface, Repository repository) {
        this.countryViewInterface = countryViewInterface;
        this.repository = repository;
    }
    public void mealsOfCountry(String CountryName){
        repository.repoFilterByCountry(this,CountryName);
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
        countryViewInterface.showAllMealsOfCountry(meal);

    }



    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);
    }
}
