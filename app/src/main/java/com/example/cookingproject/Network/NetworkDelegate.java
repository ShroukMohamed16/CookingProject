package com.example.cookingproject.Network;

import com.example.cookingproject.Model.Meal;

import java.util.List;

public interface NetworkDelegate {
     void onSuccessResponse(List<Meal> meals);
     void onFailureResponse(String errorMsg);
}
