package com.example.cookingproject.plan.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.favorite.presenter.FavoritePresenter;
import com.example.cookingproject.favorite.view.FavoriteAdapter;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;
import com.example.cookingproject.plan.presenter.PlanPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PlanFragment extends Fragment implements onClickListenerPlan , PlanViewInterface{
    RecyclerView planRecyclerView;
    PlanPresenter presenter;
    PlanAdapter planAdapter;
    List<String> days ;




    public PlanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_plan, container, false);
        days = Arrays.asList("Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday");
        planRecyclerView = view.findViewById(R.id.plan_rv);
        planAdapter = new PlanAdapter(getContext(),days,this);
        planRecyclerView.setAdapter(planAdapter);
        planRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
       // presenter = new PlanPresenter(this , Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));

        return view;
    }
}