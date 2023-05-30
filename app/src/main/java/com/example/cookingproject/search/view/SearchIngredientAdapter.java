package com.example.cookingproject.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cookingproject.Model.Ingredient;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.R;
import com.example.cookingproject.meals.view.IngredientAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchIngredientAdapter extends RecyclerView.Adapter<SearchIngredientAdapter.ViewHolder> {
    private Context context;
    private List<Ingredient> ingredients ;
    onClickListener onClickListener;

    public SearchIngredientAdapter(Context context, ArrayList<Ingredient> ingredients,onClickListener onClickListener) {
        this.context = context;
        this.ingredients = ingredients;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public SearchIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients,parent,false);
        SearchIngredientAdapter.ViewHolder viewHolder = new SearchIngredientAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchIngredientAdapter.ViewHolder holder, int position) {
        System.out.println("OKAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAY");
        holder.name.setText(ingredients.get(position).getStrIngredient());
        String name = ingredients.get(position).getStrIngredient();
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/"+ name+"-Small.png")
                .apply(new RequestOptions().override(60,60)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        if(ingredients == null){
            return 0;
        }
        return ingredients.size();
    }

    public void setList(List<Ingredient> updateList)
    {
        this.ingredients = updateList;
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
