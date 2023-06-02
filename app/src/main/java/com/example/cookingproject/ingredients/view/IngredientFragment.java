package com.example.cookingproject.ingredients.view;

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
import com.example.cookingproject.ingredients.presenter.IngredientPresenter;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;

import java.util.ArrayList;
import java.util.List;


public class IngredientFragment extends Fragment implements onClickListner,IngredientInterface{

    RecyclerView recyclerView ;
    IngredientMealsAdapter ingredientMealsAdapter;
    IngredientPresenter ingredientPresenter;
    TextView ingredient;
    String ingredient_name;
    public IngredientFragment(){

    }
    @Override
    public void onStart() {
        super.onStart();
        ((HomeActivity) requireActivity()).bottomNavigationView.setVisibility(View.GONE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        recyclerView = view.findViewById(R.id.recyclerIngredient);
        ingredient = view.findViewById(R.id.ingredientText);
        ingredientMealsAdapter  = new IngredientMealsAdapter(getContext(),new ArrayList<>(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(ingredientMealsAdapter);
        ingredient_name = IngredientFragmentArgs.fromBundle(getArguments()).getIngredientName();
        ingredient.setText(ingredient_name);
        ingredientPresenter = new IngredientPresenter(this , Repository.getInstance(ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        ingredientPresenter.getMealsByIngredient(ingredient_name);
        return view;
    }

    @Override
    public void showAllMealsOfIngredient(List<Meal> meals) {
         ingredientMealsAdapter.setList(meals);
         ingredientMealsAdapter.notifyDataSetChanged();

    }

    @Override
    public void addToFav(Meal meal) {
        ingredientPresenter.addToFav(meal);
        ingredientPresenter.uploadMeal(meal);

    }
}