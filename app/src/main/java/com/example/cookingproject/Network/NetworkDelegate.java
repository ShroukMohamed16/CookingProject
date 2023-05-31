package com.example.cookingproject.Network;

import com.example.cookingproject.Model.MealList;

public interface NetworkDelegate {
     void onSuccessResponse(MealList meals);

     void onFailureResponse(String errorMsg);
}
