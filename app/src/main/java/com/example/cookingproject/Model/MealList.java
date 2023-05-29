package com.example.cookingproject.Model;

import java.util.List;

public class MealList {

    List<Meal> meals;
    public MealList(List<Meal> meals) {
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
