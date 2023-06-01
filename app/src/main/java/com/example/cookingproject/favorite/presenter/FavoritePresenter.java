package com.example.cookingproject.favorite.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.favorite.view.FavoriteViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FavoritePresenter{

    FavoriteViewInterface favoriteViewInterface;
    Repository repository;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference MealRef = firebaseFirestore.collection("Meal Details");
    List<Meal> favMeals = new ArrayList<>();

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
  public void uploadMeal(Meal meal) {
        if(!meal.isFavorite()){
            meal.setFavorite(true);
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
                        favMeals.add(meal);
                        favoriteViewInterface.showFromFirebase(favMeals);
                    }
                } else {
                    System.out.println(task.getException());
                }
            }
        });
        }
    public void deleteFromFirebase(Meal meal){
        Query Query = MealRef.whereEqualTo("idMeal", meal.getIdMeal());
        meal.setFavorite(false);
        Query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                WriteBatch batch = firebaseFirestore.batch();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    batch.delete(documentSnapshot.getReference());
                }
                batch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MyApp", "Documents deleted successfully");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MyApp", "Error deleting documents", e);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle any errors
            }
        });
    }




}
