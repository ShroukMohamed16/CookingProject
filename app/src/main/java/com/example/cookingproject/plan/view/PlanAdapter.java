package com.example.cookingproject.plan.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingproject.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private Context context;
    private List<String> Days;
    onClickListenerPlan onClickListenerPlan;

    public PlanAdapter(Context context, List<String> days, onClickListenerPlan onClickListenerPlan) {
        this.context = context;
        Days = days;
        this.onClickListenerPlan = onClickListenerPlan;
    }

    @NonNull
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.day,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder,final int position) {
        holder.dayName.setText(Days.get(position));
    }

    @Override
    public int getItemCount() {
        return Days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView dayName;
        MaterialButton addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.name_day);
            addButton = itemView.findViewById(R.id.btn_addToPlan);


        }
    }
}

