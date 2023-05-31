package com.example.cookingproject.categories.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.categories.presenter.CategoryPresenter;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;

import java.util.ArrayList;
import java.util.List;



public class CategoryFragment extends Fragment implements CategoryViewInterface,onClickListener{

    RecyclerView recyclerView ;
    CategoryAdapter categoryAdapter;
    CategoryPresenter categoryPresenter;
    TextView category;
    String category_name;

    public CategoryFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recyclerCategory);
        category = view.findViewById(R.id.categoryText);
        categoryAdapter  = new CategoryAdapter(getContext(),new ArrayList<>(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(categoryAdapter);
         category_name = CategoryFragmentArgs.fromBundle(getArguments()).getCategoryName();
        category.setText(category_name);
        categoryPresenter = new CategoryPresenter(this , Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        categoryPresenter.mealsOfCategory(category_name);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClickAddToFav(Meal meal) {
        categoryPresenter.addToFav(meal);
    }

    @Override
    public void showAllMealsOfCategory(List<Meal> meals) {
        categoryAdapter.setList(meals);
        categoryAdapter.notifyDataSetChanged();

    }
}