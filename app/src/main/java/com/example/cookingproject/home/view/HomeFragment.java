package com.example.cookingproject.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingproject.HomeActivity;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.home.presenter.Presenter;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeViewInterface,onClickListener {

    RecyclerView mealsRecyclerView;
    RecyclerView categoryRecyclerView;
    RecyclerView countryRecyclerView;

    Presenter presenter;
    String CategoryName;
    InspirationAdapter inspirationAdapter;
    AllCategoryAdapter allCategoryAdapter;
    AllCountryAdapter allCountryAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view  = inflater.inflate(R.layout.fragment_home, container, false);
        mealsRecyclerView = view.findViewById(R.id.meal_rv);
        inspirationAdapter = new InspirationAdapter(getContext(),new ArrayList<>(),this);
        mealsRecyclerView.setAdapter(inspirationAdapter);
        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        categoryRecyclerView = view.findViewById(R.id.category_rv);
        allCategoryAdapter = new AllCategoryAdapter(getContext(),new ArrayList<>(),this);
        categoryRecyclerView.setAdapter(allCategoryAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        countryRecyclerView = view.findViewById(R.id.country_rv);
        allCountryAdapter = new AllCountryAdapter(getContext(),new ArrayList<>(),this);
        countryRecyclerView.setAdapter(allCountryAdapter);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        presenter = new Presenter(this , Repository.getInstance(ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        presenter.getDailyMeals();
        presenter.getAllCategory();
        presenter.getAllCountry();
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        ((HomeActivity) requireActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showDaily(List<Meal> mealList) {
        inspirationAdapter.setList(mealList);
        inspirationAdapter.notifyDataSetChanged();

    }

    @Override
    public void showAllCategory(List<Meal> meal) {
        allCategoryAdapter.setList(meal);
        allCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void showAllCountry(List<Meal> meal) {
        allCountryAdapter.setList(meal);
        allCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickAddTofav(Meal meal) {
        presenter.addToFav(meal);
       // presenter.uploadMeal(meal);
    }
}