package com.example.cookingproject.home.view;

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
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class InspirationAdapter extends RecyclerView.Adapter<InspirationAdapter.ViewHolder>{
    private Context context;
    private List<Meal> meals;
    private HomeViewInterface viewInterface;

    public InspirationAdapter(Context context, ArrayList<Meal> meals, HomeViewInterface viewInterface) {
        this.context = context;
        this.meals = meals;
        this.viewInterface = viewInterface;
    }

    @NonNull
    @Override
    public InspirationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InspirationAdapter.ViewHolder holder, int position) {
        holder.mealName.setText(meals.get(position).getStrMeal());
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.meal_img);
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
