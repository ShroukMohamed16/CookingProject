package com.example.cookingproject.Network;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;

import java.util.List;

public interface NetworkDelegate {
     void onSuccessResponse(MealList meals);
     void onFailureResponse(String errorMsg);
}
