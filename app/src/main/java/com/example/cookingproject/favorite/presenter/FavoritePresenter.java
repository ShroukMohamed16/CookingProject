package com.example.cookingproject.favorite.presenter;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.favorite.view.FavoriteViewInterface;

public class FavoritePresenter{

    FavoriteViewInterface favoriteViewInterface;
    Repository repository;

    public FavoritePresenter(FavoriteViewInterface favoriteViewInterface, Repository repository) {
        this.favoriteViewInterface = favoriteViewInterface;
        this.repository = repository;
    }
    public void getFavMeals(){
        favoriteViewInterface.showFavMeals(repository.repoGetAllFavMeals());
    }
    public void deleteFromFav(Meal meal){
        repository.repoDeleteFromFav(meal);

    }




}
