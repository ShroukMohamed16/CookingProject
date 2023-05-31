package com.example.cookingproject.Network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cookingproject.Model.MealList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealClient implements RemoteSource{
    private static final String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "MealClient";
    private static MealClient instance = null;
    static MealServices mealServices ;


    private MealClient(){

    }

    public static MealClient getInstance(){
        if(instance == null){
            instance = new MealClient();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client2 = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mealServices = retrofit.create(MealServices.class);
        }
        return instance;
    }


    @Override
    public void enqueueCallDailyInspirationMeals(NetworkDelegate networkDelegate) {
        Call<MealList> call = mealServices.getDailyInspirationMeals();
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(@NonNull Call<MealList> call, @NonNull Response<MealList> response) {
                Log.i(TAG, "onResponse: Success"+ response.body().getMeals().get(0).getStrMeal());
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MealList> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });


    }

    @Override
    public void enqueueCallListAllByCategory(NetworkDelegate networkDelegate) {
        Call<MealList> call = mealServices.listAllByCategory();
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(@NonNull Call<MealList> call, @NonNull Response<MealList> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MealList> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });


    }

    @Override
    public void enqueueCallListAllByCountry(NetworkDelegate networkDelegate) {
        Call<MealList> call = mealServices.listAllByCountry();
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(@NonNull Call<MealList> call, @NonNull Response<MealList> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MealList> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallListAllByIngredient(NetworkDelegate networkDelegate) {
        Call<MealList> call = mealServices.listAllByIngredient();
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(@NonNull Call<MealList> call, @NonNull Response<MealList> response) {
                networkDelegate.onSuccessResponse(response.body());

            }
            @Override
            public void onFailure(@NonNull Call<MealList> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void enqueueCallSearchByName(NetworkDelegate networkDelegate, String name) {
        Call<MealList> call = mealServices.searchByName(name);
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(@NonNull Call<MealList> call, @NonNull Response<MealList> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MealList> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallLookUpById(NetworkDelegate networkDelegate, int id) {
        Call<MealList> call = mealServices.lookUpById(id);
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(@NonNull Call<MealList> call, @NonNull Response<MealList> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MealList> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallFilterByCountry(NetworkDelegate networkDelegate, String country) {
        Call<MealList> call = mealServices.filterByCountry(country);
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(@NonNull Call<MealList> call, @NonNull Response<MealList> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MealList> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallFilterByCategory(NetworkDelegate networkDelegate, String category) {
        Call<MealList> call = mealServices.filterByCategory(category);
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(@NonNull Call<MealList> call, @NonNull Response<MealList> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MealList> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());

            }
        });

    }

    @Override
    public void enqueueCallFilterByIngredient(NetworkDelegate networkDelegate, String ingredient) {
        Call<MealList> call = mealServices.filterByIngredient(ingredient);
        call.enqueue(new Callback<MealList>() {
            @Override
            public void onResponse(@NonNull Call<MealList> call, @NonNull Response<MealList> response) {
                networkDelegate.onSuccessResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MealList> call, @NonNull Throwable t) {
                networkDelegate.onFailureResponse(t.getLocalizedMessage());
            }
        });

    }
}
