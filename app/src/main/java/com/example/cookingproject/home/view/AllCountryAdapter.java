package com.example.cookingproject.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllCountryAdapter extends RecyclerView.Adapter<AllCountryAdapter.ViewHolder>{
    private Context context;
    private List<Meal> meals;
    onClickListener onClickListener;

    public AllCountryAdapter(Context context, ArrayList<Meal> meals, onClickListener onClickListener) {
        this.context = context;
        this.meals = meals;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public AllCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients,parent,false);
        AllCountryAdapter.ViewHolder viewHolder = new AllCountryAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllCountryAdapter.ViewHolder holder, int position) {
        holder.name.setText(meals.get(position).getStrArea());
        holder.circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentDirections.ActionHomeFragmentToCountryFragment action  = HomeFragmentDirections.actionHomeFragmentToCountryFragment(meals.get(position).getStrArea());
                Navigation.findNavController(v).navigate(action);
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentDirections.ActionHomeFragmentToCountryFragment action  = HomeFragmentDirections.actionHomeFragmentToCountryFragment(meals.get(position).getStrArea());
                Navigation.findNavController(v).navigate(action);

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
            circleImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.location));
            name = itemView.findViewById(R.id.name);
        }
    }

}
