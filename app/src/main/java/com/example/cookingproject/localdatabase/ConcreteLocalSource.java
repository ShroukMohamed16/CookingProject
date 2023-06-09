package com.example.cookingproject.localdatabase;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.cookingproject.Model.Meal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource{

    private static ConcreteLocalSource instance = null;
    private MealDAO mealDAO;
    private LiveData<List<Meal>> allFavouritesMeals;
    private LiveData<List<Meal>> allPlan;

    public ConcreteLocalSource(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context.getApplicationContext());
        mealDAO = appDatabase.mealDAO();
        allFavouritesMeals = mealDAO.getAllFavouriteMeals();
        allPlan = mealDAO.getPlanMeals();
    }

    public static ConcreteLocalSource getInstance(Context context) {
        if(instance == null){
            instance = new ConcreteLocalSource(context);
        }
        return instance;
    }

    @Override
    public void insertToFav(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertToFavourite(meal);
            }
        }).start();

    }

    @Override
    public void deleteFromFav(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteFromFavourite(meal);
            }
        }).start();

    }

    @Override
    public LiveData<List<Meal>> getAllFavMeals() {
        return allFavouritesMeals;
    }


    @Override
    public LiveData<List<Meal>> getPlan() {
        return allPlan;
    }

    @Override
    public void DeleteData() {
        Log.i("TAG", "DeleteData: from concerete");

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.clearRoom();
            }
        }).start();
    }
}
