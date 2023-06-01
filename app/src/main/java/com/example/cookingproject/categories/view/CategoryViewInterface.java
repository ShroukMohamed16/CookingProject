package com.example.cookingproject.categories.view;

import com.example.cookingproject.Model.Meal;

import java.util.List;

public interface CategoryViewInterface {
    void showAllMealsOfCategory(List<Meal> meals);
    void showFromFirebase(List<Meal> favMeals);
}
