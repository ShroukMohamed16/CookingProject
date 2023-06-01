package com.example.cookingproject.home.presenter;

import androidx.annotation.NonNull;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.home.view.HomeViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Presenter implements NetworkDelegate {
    HomeViewInterface  homeViewInterface;
    Repository repository;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference MealRef = firebaseFirestore.collection("Meal Details");
    List<Meal> favMeals = new ArrayList<>();
    public Presenter(HomeViewInterface homeViewInterface, Repository repository) {
        this.homeViewInterface = homeViewInterface;
        this.repository = repository;
    }
    public void getDailyMeals(){
        repository.repoDailyInspirationMeals(this);
    }
    public void addToFav(Meal meal){
        if(!meal.isFavorite()) {
            meal.setFavorite(true);
            repository.repoInsertToFav(meal);
        }

    }
    public void getAllCategory(){
        repository.repoListAllByCategory(this);
    }
    public void getAllCountry(){repository.repoListAllByCountry(this);};

    @Override
    public void onSuccessResponse(MealList mealList) {
        List<Meal> meal= mealList.getMeals();
        if(meal.get(0).getStrMeal() != null) {
            homeViewInterface.showDaily(meal);
        }
        else if(meal.get(0).getStrArea() == null) {
            homeViewInterface.showAllCategory(meal);
        }
        else if(meal.get(0).getStrCategory() == null){
            homeViewInterface.showAllCountry(meal);
        }
    }



    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);

    }
    public void uploadMeal(Meal meal) {
        meal.setIdMeal(String.valueOf(System.currentTimeMillis()));
        meal.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
        firebaseFirestore.collection("Meal Details")
                .document(meal.getIdMeal()).set(meal)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Success");
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            System.out.println(errorMessage);
                        }
                    }
                });
    }
}
