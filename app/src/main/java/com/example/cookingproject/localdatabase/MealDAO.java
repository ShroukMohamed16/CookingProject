package com.example.cookingproject.localdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cookingproject.Model.Meal;

import java.util.List;

@Dao
public interface MealDAO {

    @Query("select * from meals where isFavorite = 1 ")
    public LiveData<List<Meal>> getAllFavouriteMeals();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    public void insertToFavourite(Meal meal);

    @Delete
    public void deleteFromFavourite(Meal meal);

   @Query("select * from meals where day != '' or day != null ")
    public LiveData<List<Meal>> getPlanMeals();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    public void insertToPlan(Meal meal);

    @Delete
    public void deleteFromPlan(Meal meal);

    @Query("DELETE FROM meals")
    void clearRoom();



}
