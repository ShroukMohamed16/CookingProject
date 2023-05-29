package com.example.cookingproject.favorite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.favorite.presenter.FavoritePresenter;
import com.example.cookingproject.home.presenter.Presenter;
import com.example.cookingproject.home.view.InspirationAdapter;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements FavoriteViewInterface,onClickListner{

    RecyclerView favMealsRecyclerView;
    FavoritePresenter presenter;
    FavoriteAdapter favoriteAdapter;


    public FavoriteFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_favorite, container, false);
        favMealsRecyclerView = view.findViewById(R.id.fav_rv);
        favoriteAdapter = new FavoriteAdapter(getContext(),new ArrayList<>(),this);
        favMealsRecyclerView.setAdapter(favoriteAdapter);
        favMealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        presenter = new FavoritePresenter(this , Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        presenter.getFavMeals();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void showFavMeals(LiveData<List<Meal>> meals) {
        meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealLive) {
                favoriteAdapter.setList(mealLive);
                favMealsRecyclerView.setAdapter(favoriteAdapter);
            }
        });

    }

    @Override
    public void deleteMeal(Meal meal) {
        presenter.deleteFromFav(meal);
    }

    @Override
    public void onClick(Meal meal) {
        deleteMeal(meal);
        favoriteAdapter.notifyDataSetChanged();

    }
}