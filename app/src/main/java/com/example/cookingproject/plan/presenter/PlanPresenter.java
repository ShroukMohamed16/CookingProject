package com.example.cookingproject.plan.presenter;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.favorite.view.FavoriteViewInterface;
import com.example.cookingproject.plan.view.PlanViewInterface;
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

public class PlanPresenter {

     PlanViewInterface planViewInterface;
    Repository repository;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference MealRef = firebaseFirestore.collection("Meals Plan");
    List<Meal> planMeals = new ArrayList<>();

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
    public void getMealsFromFirebase() {
        Query query = MealRef.whereEqualTo("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> documents = task.getResult().getDocuments();
                    for (DocumentSnapshot document : documents) {
                        // Get the document data as a custom object
                        Meal meal = document.toObject(Meal.class);
                        planMeals.add(meal);
                        planViewInterface.showPlansFromFirebase(planMeals);
                    }
                } else {
                    System.out.println(task.getException());
                }
            }
        });
    }


}
