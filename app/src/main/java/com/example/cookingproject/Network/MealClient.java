package com.example.cookingproject.Network;

import androidx.annotation.NonNull;

import com.example.cookingproject.Model.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClient implements RemoteSource{
    private final String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "MealClient";
    private static MealClient instance = null;
    MealServices mealServices ;


    private MealClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealServices = retrofit.create(MealServices.class);

    }
    public static MealClient getInstance(){
        if(instance == null){
            instance = new MealClient();
        }
        return instance;
    }


    @Override
    public void enqueueCallDailyInspirationMeals(NetworkDelegate networkDelegate) {
        Call<List<Meal>> call = mealServices.getDailyInspirationMeals();
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });


    }

    @Override
    public void enqueueCallListAllByCategory(NetworkDelegate networkDelegate) {
        Call<List<Meal>> call = mealServices.listAllByCategory();
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });


    }

    @Override
    public void enqueueCallListAllByCountry(NetworkDelegate networkDelegate) {
        Call<List<Meal>> call = mealServices.listAllByCountry();
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallListAllByIngredient(NetworkDelegate networkDelegate) {
        Call<List<Meal>> call = mealServices.listAllByIngredient();
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                networkDelegate.onSuccessResponse(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void enqueueCallSearchByName(NetworkDelegate networkDelegate, String name) {
        Call<List<Meal>> call = mealServices.searchByName(name);
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallLookUpById(NetworkDelegate networkDelegate, int id) {
        Call<List<Meal>> call = mealServices.lookUpById(id);
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallFilterByCountry(NetworkDelegate networkDelegate, String country) {
        Call<List<Meal>> call = mealServices.filterByCountry(country);
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallFilterByCategory(NetworkDelegate networkDelegate, String category) {
        Call<List<Meal>> call = mealServices.filterByCategory(category);
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallFilterByIngredient(NetworkDelegate networkDelegate, String ingredient) {
        Call<List<Meal>> call = mealServices.filterByIngredient(ingredient);
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meal>> call, @NonNull Response<List<Meal>> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meal>> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());
            }
        });

    }
}
