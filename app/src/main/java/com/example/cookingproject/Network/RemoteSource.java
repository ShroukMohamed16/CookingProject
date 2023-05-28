package com.example.cookingproject.Network;

public interface RemoteSource {
    void enqueueCallDailyInspirationMeals(NetworkDelegate networkDelegate);

    void enqueueCallListAllByCategory(NetworkDelegate networkDelegate);

    void enqueueCallListAllByCountry(NetworkDelegate networkDelegate);

    void enqueueCallListAllByIngredient(NetworkDelegate networkDelegate);

    void enqueueCallSearchByName(NetworkDelegate networkDelegate, String name);

    void enqueueCallLookUpById(NetworkDelegate networkDelegate, int id);

    void enqueueCallFilterByCountry(NetworkDelegate networkDelegate, String country);

    void enqueueCallFilterByCategory(NetworkDelegate networkDelegate, String category);

    void enqueueCallFilterByIngredient(NetworkDelegate networkDelegate, String ingredient);


}
