package com.example.cookingproject.plan.view;

import androidx.lifecycle.LiveData;

import com.example.cookingproject.Model.Meal;

import java.util.List;

public interface PlanViewInterface {

    void showPlanMeals(LiveData<List<Meal>> meals);
    void deleteMealFromPlan(Meal meal);
}
