package com.example.cookingproject.favorite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.R;
import com.example.cookingproject.R.drawable;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    private Context context;
    private List<Meal> meals;
    private onClickListner onClickListner;

    public FavoriteAdapter(Context context, ArrayList<Meal> meals, onClickListner onClickListner) {
        this.context = context;
        this.meals = meals;
        this.onClickListner = onClickListner;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item,parent,false);
        FavoriteAdapter.ViewHolder viewHolder = new FavoriteAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        holder.mealName.setText(meals.get(position).getStrMeal());
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.meal_img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteFragmentDirections.ActionFavoriteFragmentToMealFragment action = FavoriteFragmentDirections.actionFavoriteFragmentToMealFragment(meals.get(position).getStrMeal());
                Navigation.findNavController(v).navigate(action);
            }
        });

        holder.deleteFromFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListner.onClick(meals.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public  void setList(List<Meal> updateList)
    {
        this.meals = updateList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView mealName;
        MaterialButton deleteFromFavButton;
        ImageView meal_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.meal_card);
            mealName = itemView.findViewById(R.id.meal_name);
            deleteFromFavButton = itemView.findViewById(R.id.addToFavButton);
            meal_img = itemView.findViewById(R.id.meal_img);
            deleteFromFavButton.setText("Delete From Favourite");
            deleteFromFavButton.setIcon(ContextCompat.getDrawable(context, drawable.trash_full));


        }
    }

}
