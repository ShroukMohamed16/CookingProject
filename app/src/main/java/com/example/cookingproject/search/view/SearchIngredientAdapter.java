package com.example.cookingproject.search.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingproject.Model.Meal;

import java.util.List;

public class SearchIngredientAdapter extends RecyclerView.Adapter<SearchIngredientAdapter.ViewHolder> {
    Context context;
    List<Meal> mealList;
    onClickListener onClickListener;

    public SearchIngredientAdapter(Context context, List<Meal> mealList, onClickListener onClickListener) {
        this.context = context;
        this.mealList = mealList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public SearchIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchIngredientAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
