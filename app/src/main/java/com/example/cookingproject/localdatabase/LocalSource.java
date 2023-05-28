package com.example.cookingproject.localdatabase;

import androidx.lifecycle.LiveData;

import com.example.cookingproject.Model.Meal;

import java.util.List;

public interface LocalSource {

     void insertToFav(Meal meal);
     void deleteFromFav(Meal meal);
     LiveData<List<Meal>> getAllFavMeals();
}
