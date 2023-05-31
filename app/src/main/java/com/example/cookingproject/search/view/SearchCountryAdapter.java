package com.example.cookingproject.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.R;


import java.util.ArrayList;
import java.util.List;

public class SearchCountryAdapter extends RecyclerView.Adapter<SearchCountryAdapter.ViewHolder>{
    private Context context;
    private List<Meal> meals;
    onClickListener onClickListener;
    String[] countriesFlags ;

    public SearchCountryAdapter(Context context, ArrayList<Meal> meals, onClickListener onClickListener) {
        this.context = context;
        this.meals = meals;
        this.onClickListener = onClickListener;
        countriesFlags = context.getResources().getStringArray(R.array.flags);
    }

    @NonNull
    @Override
    public SearchCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_card,parent,false);
        SearchCountryAdapter.ViewHolder viewHolder = new SearchCountryAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCountryAdapter.ViewHolder holder, int position) {
        holder.name.setText(meals.get(position).getStrArea());
        if(position < countriesFlags.length) {
            Glide.with(context).load(countriesFlags[position])
                    .apply(new RequestOptions().override(150, 150)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_foreground)).into(holder.circleImageView);
        }
       holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFragmentToCountryFragment action  = SearchFragmentDirections.actionSearchFragmentToCountryFragment(meals.get(position).getStrArea());
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
        CardView cardView;
        ImageView circleImageView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            circleImageView  = itemView.findViewById(R.id.ImageView);
            name = itemView.findViewById(R.id.name);
        }
    }

}
