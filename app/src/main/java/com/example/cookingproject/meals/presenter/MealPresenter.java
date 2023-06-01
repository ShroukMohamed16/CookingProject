package com.example.cookingproject.meals.presenter;

import androidx.annotation.NonNull;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.meals.view.mealViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MealPresenter implements NetworkDelegate {
    mealViewInterface MealInterface;
    Repository repository;
    FirebaseFirestore firebaseFirestore =FirebaseFirestore.getInstance();

    public MealPresenter(mealViewInterface MealInterface, Repository repository) {
         this.MealInterface = MealInterface;
         this.repository = repository;
    }



    public void addToPlan(Meal meal) {
    }

    public void addToFavorite(Meal meal) {
        repository.repoInsertToFav(meal);
    }

    public void getMealItem(String mealNameItem){
        repository.repoSearchByName(this , mealNameItem);
    }

    @Override
    public void onSuccessResponse(MealList meals) {
        List<Meal> meal = meals.getMeals();
        MealInterface.showMeal(meal);
    }



    @Override
    public void onFailureResponse(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void uploadMeal(Meal meal){
        meal.setIdMeal(String.valueOf(System.currentTimeMillis()));
        meal.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
        firebaseFirestore.collection("Meal Details")
                .document(meal.getIdMeal()).set(meal)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            System.out.println("Success");
                        }else{
                            String errorMessage=task.getException().getLocalizedMessage();
                            System.out.println(errorMessage);
                        }
                    }
                });

    }
    public void uploadPlan(Meal meal){
        meal.setIdMeal(String.valueOf(System.currentTimeMillis()));
        meal.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
        firebaseFirestore.collection("Meals Plan")
                .document(meal.getIdMeal()).set(meal)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            System.out.println("Success");
                        }else{
                            String errorMessage=task.getException().getLocalizedMessage();
                            System.out.println(errorMessage);
                        }
                    }
                });

    }
}
