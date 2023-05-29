package com.example.cookingproject.Network;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MealServices {
    @GET("random.php")
    Call<MealList> getDailyInspirationMeals();
    @GET("filter.php?a={country}")
    Call<MealList> filterByCountry(@Path("country")String country);
    @GET("filter.php?i={ingredient}")
    Call<MealList> filterByIngredient(@Path("ingredient")String ingredient);
    @GET("filter.php?c={category}")
    Call<MealList> filterByCategory(@Path("category")String category);
    @GET("lookup.php?i={id}")
    Call<MealList> lookUpById(@Path("id")int id);
    @GET("search.php?s={name}")
    Call<MealList> searchByName(@Path("name")String name);
    @GET("list.php?a=list")
    Call<MealList> listAllByCountry();
    @GET("list.php?i=list")
    Call<MealList> listAllByIngredient();
    @GET("list.php?c=list")
    Call<MealList> listAllByCategory();

}
