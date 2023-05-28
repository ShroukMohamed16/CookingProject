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
import android.widget.Toast;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.home.presenter.Presenter;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeViewInterface {

    RecyclerView mealsRecyclerView;
    Presenter presenter;
    InspirationAdapter inspirationAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


         View view  = inflater.inflate(R.layout.fragment_home, container, false);
        mealsRecyclerView = view.findViewById(R.id.meal_rv);
        mealsRecyclerView.setAdapter(new InspirationAdapter(getContext(),new ArrayList<>(),this));
        mealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        presenter = new Presenter(this , Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        presenter.getDailyMeals();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void showDaily(List<Meal> mealList) {
        inspirationAdapter.setList(mealList);
        inspirationAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Okay", Toast.LENGTH_SHORT).show();
;
    }
}