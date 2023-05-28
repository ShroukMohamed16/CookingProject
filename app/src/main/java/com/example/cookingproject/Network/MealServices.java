package com.example.cookingproject.Network;

import com.example.cookingproject.Model.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MealServices {
    @GET("randomselection.php")
    Call<List<Meal>> getDailyInspirationMeals();
    @GET("filter.php?a={country}")
    Call<List<Meal>> filterByCountry(@Path("country")String country);
    @GET("filter.php?i={ingredient}")
    Call<List<Meal>> filterByIngredient(@Path("ingredient")String ingredient);
    @GET("filter.php?c={category}")
    Call<List<Meal>> filterByCategory(@Path("category")String category);
    @GET("lookup.php?i={id}")
    Call<List<Meal>> lookUpById(@Path("id")int id);
    @GET("search.php?s={name}")
    Call<List<Meal>> searchByName(@Path("name")String name);
    @GET("list.php?a=list")
    Call<List<Meal>> listAllByCountry();
    @GET("list.php?i=list")
    Call<List<Meal>> listAllByIngredient();
    @GET("list.php?c=list")
    Call<List<Meal>> listAllByCategory();

}
