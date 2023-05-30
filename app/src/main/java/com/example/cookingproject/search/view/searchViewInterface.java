package com.example.cookingproject.search.view;

import com.example.cookingproject.Model.Meal;

import java.util.List;

public interface searchViewInterface {
    void showAllIngredient(List<Meal> meal);
    void showAllCategory(List<Meal> meal);
    void showAllCountry(List<Meal> meal);
}
