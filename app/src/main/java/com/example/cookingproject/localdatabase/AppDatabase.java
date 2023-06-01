package com.example.cookingproject.localdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cookingproject.Model.Meal;

@Database(entities = Meal.class, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase instance = null;

    public abstract MealDAO mealDAO();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Meal Database")
                    .fallbackToDestructiveMigration()
                    .build();


        }
        return instance;
    }
}
