package com.example.cookingproject.home.view;

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
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ViewHolder>{
    private Context context;
    private List<Meal> meals;
    onClickListener onClickListener;

    public AllCategoryAdapter(Context context, ArrayList<Meal> meals, onClickListener onClickListener) {
        this.context = context;
        this.meals = meals;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public AllCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients,parent,false);
        AllCategoryAdapter.ViewHolder viewHolder = new AllCategoryAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryAdapter.ViewHolder holder, int position) {
        holder.name.setText(meals.get(position).getStrCategory());
        String category_name = meals.get(position).getStrCategory();
        Glide.with(context).load("https://www.themealdb.com/images/category/"+ category_name+".png")
                .apply(new RequestOptions().override(60,60)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)).into(holder.circleImageView);
        holder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(HomeFragmentDirections.actionHomeFragmentToCategoryFragment2(meals.get(position).getStrCategory()));

            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(HomeFragmentDirections.actionHomeFragmentToCategoryFragment2(meals.get(position).getStrCategory()));

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
        CircleImageView circleImageView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView  = itemView.findViewById(R.id.circleImageView);
            name = itemView.findViewById(R.id.name);
        }
    }

}
