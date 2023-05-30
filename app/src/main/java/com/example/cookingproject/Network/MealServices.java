package com.example.cookingproject.Network;

import com.example.cookingproject.Model.Ingredient;
import com.example.cookingproject.Model.IngredientList;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MealServices {
    @GET("random.php")
    Call<MealList> getDailyInspirationMeals();
    @GET("filter.php")
    Call<MealList> filterByCountry(@Query("a") String country);
    @GET("filter.php")
    Call<MealList> filterByIngredient(@Query("i") String ingredient);
    @GET("filter.php")
    Call<MealList> filterByCategory(@Query("c") String category);
    @GET("lookup.php?i={id}")
    Call<MealList> lookUpById(@Path("id")int id);
    @GET("search.php")
    Call<MealList> searchByName(@Query("s") String name);
    @GET("list.php?a=list")
    Call<MealList> listAllByCountry();
    @GET("list.php?i=list")
    Call<IngredientList> listAllByIngredient();
    @GET("list.php?c=list")
    Call<MealList> listAllByCategory();
}
