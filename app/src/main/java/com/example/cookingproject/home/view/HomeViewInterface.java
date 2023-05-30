package com.example.cookingproject.home.view;

import com.example.cookingproject.Model.Meal;

import java.util.List;

public interface HomeViewInterface {
    void showDaily(List<Meal> mealList);
    void showAllCategory(List<Meal> meal);
    void showAllCountry(List<Meal> meal);
}
