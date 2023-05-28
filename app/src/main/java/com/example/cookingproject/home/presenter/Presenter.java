package com.example.cookingproject.home.presenter;

import com.example.cookingproject.Model.Meal;
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

    @Override
    public void onSuccessResponse(List<Meal> meals) {
        homeViewInterface.showDaily(meals);
    }

    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);

    }
}
