package com.example.cookingproject.Model;

import androidx.lifecycle.LiveData;

import com.example.cookingproject.Network.NetworkDelegate;

import java.util.List;

public interface RepoInterface {

    void repoInsertToFav(Meal meal);

    void repoDeleteFromFav(Meal meal);

    LiveData<List<Meal>> repoGetAllFavMeals();

    void repoDailyInspirationMeals(NetworkDelegate networkDelegate);

    void repoListAllByCategory(NetworkDelegate networkDelegate);

    void repoListAllByCountry(NetworkDelegate networkDelegate);

    void repoListAllByIngredient(NetworkDelegate networkDelegate);

    void repoSearchByName(NetworkDelegate networkDelegate, String name);

    void repoLookUpById(NetworkDelegate networkDelegate, int id);

    void repoFilterByCountry(NetworkDelegate networkDelegate, String country);

    void repoFilterByCategory(NetworkDelegate networkDelegate, String category);

    void repoFilterByIngredient(NetworkDelegate networkDelegate, String ingredient);

}
