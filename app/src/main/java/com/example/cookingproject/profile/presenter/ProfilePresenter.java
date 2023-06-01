package com.example.cookingproject.profile.presenter;


import android.util.Log;

import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.favorite.view.FavoriteViewInterface;
import com.example.cookingproject.plan.view.PlanViewInterface;


public class ProfilePresenter {

    Repository repository;

    public ProfilePresenter( Repository repository) {
        this.repository = repository;
    }
    public void clear(){
        Log.i("TAG", "clear: ");
        repository.repoDeleteRoom();
    }
}
