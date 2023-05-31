package com.example.cookingproject.plan.view;

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

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder>{
    private Context context;
    private List<Meal> meals;
    private onClickListenerPlan onClickListner;

    public DayAdapter(Context context, ArrayList<Meal> meals, onClickListenerPlan onClickListner) {
        this.context = context;
        this.meals = meals;
        this.onClickListner = onClickListner;
    }

    @NonNull
    @Override
    public DayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_item,parent,false);
        DayAdapter.ViewHolder viewHolder = new DayAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapter.ViewHolder holder, int position) {
        holder.mealName.setText(meals.get(position).getStrMeal());
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb()).placeholder(drawable.ic_launcher_background)
                .error(drawable.ic_launcher_foreground)
                .into(holder.meal_img);

        holder.deleteFromFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListner.onClick(meals.get(position));

            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlanFragmentDirections.ActionPlanFragmentToMealFragment action = PlanFragmentDirections.actionPlanFragmentToMealFragment(meals.get(position).getStrMeal());
                Navigation.findNavController(v).navigate(action);

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
            deleteFromFavButton.setText("Delete From Plan");
            deleteFromFavButton.setIcon(ContextCompat.getDrawable(context, drawable.trash_full));


        }
    }

}
