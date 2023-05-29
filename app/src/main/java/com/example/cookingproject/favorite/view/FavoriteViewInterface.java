package com.example.cookingproject.favorite.view;

import androidx.lifecycle.LiveData;

import com.example.cookingproject.Model.Meal;

import java.util.List;

public interface FavoriteViewInterface {
    void showFavMeals(LiveData<List<Meal>> meals);
    void deleteMeal(Meal meal);
}
