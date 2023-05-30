package com.example.cookingproject.countries.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.R;
import com.example.cookingproject.categories.view.onClickListener;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{
    private Context context;
    private List<Meal> meals;
    private onClickListenerCountry onClickListener;

    public CountryAdapter(Context context, ArrayList<Meal> meals, onClickListenerCountry onClickListener) {
        this.context = context;
        this.meals = meals;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        holder.mealName.setText(meals.get(position).getStrMeal());
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.meal_img);
        holder.addToFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClickAddToFav(meals.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setList(List<Meal> updateList)
    {
        this.meals = updateList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView mealName;
        MaterialButton addToFavButton;
        ImageView meal_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.meal_card);
            mealName = itemView.findViewById(R.id.meal_name);
            addToFavButton = itemView.findViewById(R.id.addToFavButton);
            meal_img = itemView.findViewById(R.id.meal_img);
        }
    }

}
