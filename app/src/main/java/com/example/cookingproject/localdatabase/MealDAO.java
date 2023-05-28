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

    @Query("select * from meals")
    public LiveData<List<Meal>> getAllFavouriteMeals();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    public void insertToFavourite(Meal meal);

    @Delete
    public void deleteFromFavourite(Meal meal);


}
