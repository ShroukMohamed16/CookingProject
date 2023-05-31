package com.example.cookingproject.plan.presenter;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.favorite.view.FavoriteViewInterface;
import com.example.cookingproject.plan.view.PlanViewInterface;

public class PlanPresenter {

     PlanViewInterface planViewInterface;
    Repository repository;

    public PlanPresenter(PlanViewInterface planViewInterface, Repository repository) {
        this.planViewInterface = planViewInterface;
        this.repository = repository;
    }
    public void getPlanMeals(){
        planViewInterface.showPlanMeals(repository.repoGetAllPlanMeals());
    }
    public void deleteFromFav(Meal meal){
        repository.repoDeleteFromFav(meal);

    }
}
