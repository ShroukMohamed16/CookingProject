package com.example.cookingproject.plan.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookingproject.HomeActivity;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;
import com.example.cookingproject.plan.presenter.PlanPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class PlanFragment extends Fragment implements onClickListenerPlan , PlanViewInterface{

    RecyclerView saturdayRecyclerView,sundayRecyclerView,mondayRecyclerView,tuesdayRecyclerView
            ,wednesdayRecyclerView,thursdayRecyclerView,fridayRecyclerView;
    DayAdapter saturdayAdapter,sundayAdapter,mondayAdapter,tuesdayAdapter,wednesdayAdapter
            ,thursdayAdapter,fridayAdapter;
    MaterialButton addPlan;
    PlanPresenter presenter;
    LinearLayoutManager linearLayoutManager;


    @Override
    public void onStart() {
        super.onStart();
        ((HomeActivity) requireActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    }

    public PlanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_plan, container, false);

        saturdayRecyclerView = view.findViewById(R.id.saturday_rv);
        sundayRecyclerView = view.findViewById(R.id.sunday_rv);
        mondayRecyclerView = view.findViewById(R.id.monday_rv);
        tuesdayRecyclerView = view.findViewById(R.id.tuesday_rv);
        wednesdayRecyclerView = view.findViewById(R.id.wednesday_rv);
        thursdayRecyclerView = view.findViewById(R.id.thursday_rv);
        fridayRecyclerView = view.findViewById(R.id.friday_rv);
        addPlan = view.findViewById(R.id.btn_addToPlan);

        if(FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().isAnonymous()){
            addPlan.setVisibility(View.INVISIBLE);
        }

        saturdayAdapter = new DayAdapter(getContext(),new ArrayList<>(),this);
        sundayAdapter = new DayAdapter(getContext(),new ArrayList<>(),this);
        mondayAdapter = new DayAdapter(getContext(),new ArrayList<>(),this);
        tuesdayAdapter = new DayAdapter(getContext(),new ArrayList<>(),this);
        wednesdayAdapter = new DayAdapter(getContext(),new ArrayList<>(),this);
        thursdayAdapter = new DayAdapter(getContext(),new ArrayList<>(),this);
        fridayAdapter = new DayAdapter(getContext(),new ArrayList<>(),this);


        saturdayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        saturdayRecyclerView.setAdapter(saturdayAdapter);

        sundayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        sundayRecyclerView.setAdapter(sundayAdapter);

        mondayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mondayRecyclerView.setAdapter(mondayAdapter);

        tuesdayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        tuesdayRecyclerView.setAdapter(tuesdayAdapter);

        wednesdayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        wednesdayRecyclerView.setAdapter(wednesdayAdapter);

        thursdayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        thursdayRecyclerView.setAdapter(thursdayAdapter);

        fridayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        fridayRecyclerView.setAdapter(fridayAdapter);

        presenter = new PlanPresenter(this ,  Repository.getInstance(ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            presenter.getMealsFromFirebase();
        } else {
            presenter.getPlanMeals();

        }




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(PlanFragmentDirections.actionPlanFragmentToFavoriteFragment());
            }
        });
    }

    @Override
    public void onClick(Meal meal) {
        deleteMealFromPlan(meal);

        saturdayAdapter.notifyDataSetChanged();
        sundayAdapter.notifyDataSetChanged();
        mondayAdapter.notifyDataSetChanged();
        tuesdayAdapter.notifyDataSetChanged();
        wednesdayAdapter.notifyDataSetChanged();
        thursdayAdapter.notifyDataSetChanged();
        fridayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPlanMeals(LiveData<List<Meal>> meals) {
        meals.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealLive) {

                ArrayList<Meal> saturdayMeals = new ArrayList<>();
                ArrayList<Meal> sundayMeals = new ArrayList<>();
                ArrayList<Meal> mondayMeals = new ArrayList<>();
                ArrayList<Meal> tuesdayMeals = new ArrayList<>();
                ArrayList<Meal> wednesdayMeals = new ArrayList<>();
                ArrayList<Meal> thursdayMeals = new ArrayList<>();
                ArrayList<Meal> fridayMeals = new ArrayList<>();
                for(int i = 0 ; i < mealLive.size();i++){
                    System.out.println(mealLive.get(i).getNameDay());
                    if(mealLive.get(i).getNameDay().equals("Saturday"))
                        saturdayMeals.add(mealLive.get(i));
                    else if(mealLive.get(i).getNameDay().equals("Sunday"))
                        sundayMeals.add(mealLive.get(i));
                    else if(mealLive.get(i).getNameDay().equals("Monday"))
                        mondayMeals.add(mealLive.get(i));
                    else if(mealLive.get(i).getNameDay().equals("Tuesday"))
                        tuesdayMeals.add(mealLive.get(i));
                    else if(mealLive.get(i).getNameDay().equals("Wednesday"))
                        wednesdayMeals.add(mealLive.get(i));
                    else if(mealLive.get(i).getNameDay().equals("Thursday"))
                        thursdayMeals.add(mealLive.get(i));
                    else
                        fridayMeals.add(mealLive.get(i));
                }
                saturdayAdapter.setList(saturdayMeals);
                saturdayAdapter.notifyDataSetChanged();
                saturdayRecyclerView.setAdapter(saturdayAdapter);

                sundayAdapter.setList(sundayMeals);
                saturdayAdapter.notifyDataSetChanged();
                sundayRecyclerView.setAdapter(sundayAdapter);

                mondayAdapter.setList(mondayMeals);
                saturdayAdapter.notifyDataSetChanged();
                mondayRecyclerView.setAdapter(mondayAdapter);

                tuesdayAdapter.setList(tuesdayMeals);
                saturdayAdapter.notifyDataSetChanged();
                thursdayRecyclerView.setAdapter(tuesdayAdapter);

                wednesdayAdapter.setList(wednesdayMeals);
                saturdayAdapter.notifyDataSetChanged();
                wednesdayRecyclerView.setAdapter(wednesdayAdapter);

                thursdayAdapter.setList(thursdayMeals);
                saturdayAdapter.notifyDataSetChanged();
                thursdayRecyclerView.setAdapter(thursdayAdapter);

                fridayAdapter.setList(fridayMeals);
                saturdayAdapter.notifyDataSetChanged();
                fridayRecyclerView.setAdapter(fridayAdapter);

            }
        });

    }

    @Override
    public void deleteMealFromPlan(Meal meal) {
        presenter.deleteFromFav(meal);
        presenter.deleteFromFirebase(meal);

    }

    @Override
    public void showPlansFromFirebase(List<Meal> planMeals) {

        ArrayList<Meal> saturdayMeals = new ArrayList<>();
        ArrayList<Meal> sundayMeals = new ArrayList<>();
        ArrayList<Meal> mondayMeals = new ArrayList<>();
        ArrayList<Meal> tuesdayMeals = new ArrayList<>();
        ArrayList<Meal> wednesdayMeals = new ArrayList<>();
        ArrayList<Meal> thursdayMeals = new ArrayList<>();
        ArrayList<Meal> fridayMeals = new ArrayList<>();
        for(int i = 0 ; i < planMeals.size();i++){
            System.out.println(planMeals.get(i).getNameDay());
            if(planMeals.get(i).getNameDay().equals("Saturday"))
                saturdayMeals.add(planMeals.get(i));
            else if(planMeals.get(i).getNameDay().equals("Sunday"))
                sundayMeals.add(planMeals.get(i));
            else if(planMeals.get(i).getNameDay().equals("Monday"))
                mondayMeals.add(planMeals.get(i));
            else if(planMeals.get(i).getNameDay().equals("Tuesday"))
                tuesdayMeals.add(planMeals.get(i));
            else if(planMeals.get(i).getNameDay().equals("Wednesday"))
                wednesdayMeals.add(planMeals.get(i));
            else if(planMeals.get(i).getNameDay().equals("Thursday"))
                thursdayMeals.add(planMeals.get(i));
            else
                fridayMeals.add(planMeals.get(i));
        }
        saturdayAdapter.setList(saturdayMeals);
        saturdayAdapter.notifyDataSetChanged();
        saturdayRecyclerView.setAdapter(saturdayAdapter);

        sundayAdapter.setList(sundayMeals);
        saturdayAdapter.notifyDataSetChanged();
        sundayRecyclerView.setAdapter(sundayAdapter);

        mondayAdapter.setList(mondayMeals);
        saturdayAdapter.notifyDataSetChanged();
        mondayRecyclerView.setAdapter(mondayAdapter);

        tuesdayAdapter.setList(tuesdayMeals);
        saturdayAdapter.notifyDataSetChanged();
        thursdayRecyclerView.setAdapter(tuesdayAdapter);

        wednesdayAdapter.setList(wednesdayMeals);
        saturdayAdapter.notifyDataSetChanged();
        wednesdayRecyclerView.setAdapter(wednesdayAdapter);

        thursdayAdapter.setList(thursdayMeals);
        saturdayAdapter.notifyDataSetChanged();
        thursdayRecyclerView.setAdapter(thursdayAdapter);

        fridayAdapter.setList(fridayMeals);
        saturdayAdapter.notifyDataSetChanged();
        fridayRecyclerView.setAdapter(fridayAdapter);



    }
}