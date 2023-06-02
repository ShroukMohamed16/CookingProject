package com.example.cookingproject.countries.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cookingproject.HomeActivity;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.countries.presenter.CountryPresenter;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;

import java.util.ArrayList;
import java.util.List;


public class CountryFragment extends Fragment implements onClickListenerCountry , CountryViewInterface{

    RecyclerView recyclerView ;
    CountryAdapter countryAdapter;
    CountryPresenter countryPresenter;
    TextView country;
    public CountryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        ((HomeActivity) requireActivity()).bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country, container, false);
        recyclerView = view.findViewById(R.id.recyclerCountry);
        country = view.findViewById(R.id.countryText);
        countryAdapter  = new CountryAdapter(getContext(),new ArrayList<>(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(countryAdapter);
        String country_name = CountryFragmentArgs.fromBundle(getArguments()).getCountryName();
        country.setText(country_name);
        countryPresenter = new CountryPresenter(this , Repository.getInstance(ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        countryPresenter.mealsOfCountry(country_name);
        return view;
    }

    @Override
    public void showAllMealsOfCountry(List<Meal> meals) {
        countryAdapter.setList(meals);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickAddToFav(Meal meal) {
        countryPresenter.addToFav(meal);
        countryPresenter.uploadMeal(meal);

    }
}