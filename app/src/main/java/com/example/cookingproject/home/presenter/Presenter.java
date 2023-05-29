package com.example.cookingproject.home.presenter;

import android.util.Log;

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
        repository.repoInsertToFav(meal);

    }

    @Override
    public void onSuccessResponse(MealList mealList) {
        List<Meal> meal= mealList.getMeals();
        Log.i("TAG", "onSuccessResponse: Presenter"+ meal.get(0).getStrMeal());
        homeViewInterface.showDaily(meal);
    }

    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);

    }
}
