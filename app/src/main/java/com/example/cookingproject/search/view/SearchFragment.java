package com.example.cookingproject.search.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.home.presenter.Presenter;
import com.example.cookingproject.home.view.AllCategoryAdapter;
import com.example.cookingproject.home.view.AllCountryAdapter;
import com.example.cookingproject.home.view.InspirationAdapter;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;
import com.example.cookingproject.search.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements searchViewInterface , onClickListener{

    RecyclerView ingrsRecyclerView;
    RecyclerView categoryRecyclerView;
    RecyclerView countryRecyclerView;

    SearchPresenter searchPresenter;
    String IngredientName;
    String CountryName;
    String CategoryName;
    SearchIngredientAdapter searchIngredientAdapter;
    SearchCategoryAdapter searchCategoryAdapter;
    SearchCountryAdapter searchCountryAdapter;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_search, container, false);
        ingrsRecyclerView = view.findViewById(R.id.ingr_rv);
        searchIngredientAdapter = new SearchIngredientAdapter(getContext(),new ArrayList<>(),this);
        ingrsRecyclerView.setAdapter(searchIngredientAdapter);
        ingrsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        categoryRecyclerView = view.findViewById(R.id.category_rv);
        searchCategoryAdapter = new SearchCategoryAdapter(getContext(),new ArrayList<>(),this);
        categoryRecyclerView.setAdapter(searchCategoryAdapter);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        countryRecyclerView = view.findViewById(R.id.country_rv);
        searchCountryAdapter = new SearchCountryAdapter(getContext(),new ArrayList<>(),this);
        countryRecyclerView.setAdapter(searchCountryAdapter);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        searchPresenter = new SearchPresenter(this , Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        searchPresenter.getAllIngredient();
        searchPresenter.getAllCategory();
        searchPresenter.getAllCountry();
        return view;

    }

    @Override
    public void showAllIngredient(List<Meal> meal) {


    }

    @Override
    public void showAllCategory(List<Meal> meal) {
        searchCategoryAdapter.setList(meal);
        searchCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void showAllCountry(List<Meal> meal) {
        searchCountryAdapter.setList(meal);
        searchCountryAdapter.notifyDataSetChanged();

    }
}