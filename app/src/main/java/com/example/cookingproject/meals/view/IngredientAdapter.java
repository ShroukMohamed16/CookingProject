package com.example.cookingproject.meals.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cookingproject.Model.Ingredient;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.R;
import com.example.cookingproject.home.view.HomeFragmentDirections;
import com.example.cookingproject.home.view.onClickListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{
    private Context context;
    private List<Ingredient> ingredient;

    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredient) {
        this.context = context;
        this.ingredient = ingredient;
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients,parent,false);
        IngredientAdapter.ViewHolder viewHolder = new IngredientAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        holder.name.setText(ingredient.get(position).getStrIngredient());
        String name = ingredient.get(position).getStrIngredient();
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ name+"-Small.png")
                .apply(new RequestOptions().override(60,60)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return ingredient.size();
    }

    public void setList(List<Ingredient> updateList)
    {
        this.ingredient = updateList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView  = itemView.findViewById(R.id.circleImageView);
            name = itemView.findViewById(R.id.name);
        }
    }

}
