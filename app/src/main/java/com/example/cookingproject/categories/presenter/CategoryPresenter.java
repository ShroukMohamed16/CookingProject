package com.example.cookingproject.categories.presenter;

import androidx.annotation.NonNull;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.MealList;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.categories.view.CategoryViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter implements NetworkDelegate {

    CategoryViewInterface categoryViewInterface;
    Repository repository;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference MealRef = firebaseFirestore.collection("Meal Details");
    List<Meal> favMeals = new ArrayList<>();

    public CategoryPresenter(CategoryViewInterface categoryViewInterface, Repository repository) {
        this.categoryViewInterface = categoryViewInterface;
        this.repository = repository;
    }
    public void mealsOfCategory(String CategoryName){
        repository.repoFilterByCategory(this,CategoryName);
    }
    public void addToFav(Meal meal){
        if(!meal.isFavorite()) {
            meal.setFavorite(true);
            repository.repoInsertToFav(meal);
        }

    }

    @Override
    public void onSuccessResponse(MealList meals) {
        List<Meal> meal = meals.getMeals();
        categoryViewInterface.showAllMealsOfCategory(meal);

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
