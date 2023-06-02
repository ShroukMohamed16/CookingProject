package com.example.cookingproject.Model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.cookingproject.Network.NetworkDelegate;
import com.example.cookingproject.Network.RemoteSource;
import com.example.cookingproject.localdatabase.LocalSource;

import java.util.List;

public class Repository implements RepoInterface{
    private static Repository instance = null;
    LocalSource localSource;
    RemoteSource remoteSource;

    public Repository(LocalSource localSource, RemoteSource remoteSource) {

        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    public static Repository getInstance(LocalSource localSource, RemoteSource remoteSource) {
        if(instance == null){
            instance = new Repository(localSource,remoteSource);
        }
        return instance;
    }

    @Override
    public void repoInsertToFav(Meal meal) {
        localSource.insertToFav(meal);

    }

    @Override
    public void repoDeleteFromFav(Meal meal) {
        localSource.deleteFromFav(meal);

    }

    @Override
    public void repoDeleteRoom() {
        Log.i("TAG", "repoDeleteRoom: from repo ");
        localSource.DeleteData();
    }

    @Override
    public LiveData<List<Meal>> repoGetAllFavMeals() {
        return localSource.getAllFavMeals();
    }

    @Override
    public LiveData<List<Meal>> repoGetAllPlanMeals() {
         return localSource.getPlan();
    }

    @Override
    public void repoDailyInspirationMeals(NetworkDelegate networkDelegate) {
           remoteSource.enqueueCallDailyInspirationMeals(networkDelegate);
    }

    @Override
    public void repoListAllByCategory(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCallListAllByCategory(networkDelegate);

    }

    @Override
    public void repoListAllByCountry(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCallListAllByCountry(networkDelegate);

    }

    @Override
    public void repoListAllByIngredient(NetworkDelegate networkDelegate) {
        remoteSource.enqueueCallListAllByIngredient(networkDelegate);

    }

    @Override
    public void repoSearchByName(NetworkDelegate networkDelegate, String name) {
        remoteSource.enqueueCallSearchByName(networkDelegate,name);

    }

    @Override
    public void repoLookUpById(NetworkDelegate networkDelegate, int id) {
        remoteSource.enqueueCallLookUpById(networkDelegate,id);

    }

    @Override
    public void repoFilterByCountry(NetworkDelegate networkDelegate, String country) {
        remoteSource.enqueueCallFilterByCountry(networkDelegate , country);

    }

    @Override
    public void repoFilterByCategory(NetworkDelegate networkDelegate, String category) {
        remoteSource.enqueueCallFilterByCategory(networkDelegate , category);

    }

    @Override
    public void repoFilterByIngredient(NetworkDelegate networkDelegate, String ingredient) {
        remoteSource.enqueueCallFilterByIngredient(networkDelegate,ingredient);
    }
}
