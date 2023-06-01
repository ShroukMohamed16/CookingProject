package com.example.cookingproject.meals.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cookingproject.HomeActivity;
import com.example.cookingproject.Model.Ingredient;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;
import com.example.cookingproject.meals.presenter.MealPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;


public class MealFragment extends Fragment implements mealViewInterface{

    TextView mealName,mealCountry,mealSteps;
    RecyclerView recyclerView;
   ArrayList<Ingredient> ingredients = new ArrayList<>();
    IngredientAdapter ingredientAdapter;
    GridLayoutManager layoutManager;
    MaterialButton addToFav_btn , addToPlane_btn;
    ImageView imageView;
    MealPresenter mealPresenter;

    YouTubePlayerView videoView ;

    String[] videoSplit;
    String videoId;
    String []days;

    mealViewInterface mealViewInterface;
    boolean[] checkedDays;
    String selectedDay = "Saturday";
    String mealNameItem;

    public MealFragment() {
        // Required empty public constructor
    }
    @Override
    public void onStart() {
        super.onStart();
        ((HomeActivity) requireActivity()).bottomNavigationView.setVisibility(View.GONE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_meal, container, false);


        videoView =view.findViewById(R.id.videoView);
        mealName=view.findViewById(R.id.itemPageMealName);
        recyclerView=view.findViewById(R.id.ingredientRecyclerView);
        mealCountry=view.findViewById(R.id.itemPageMealCountry);
        mealSteps=view.findViewById(R.id.itemPageMealSteps);
        addToFav_btn=view.findViewById(R.id.add_to_favorite);
        addToPlane_btn=view.findViewById(R.id.add_to_calender);
        imageView=view.findViewById(R.id.profileUserImage);

        String mealNameItem = MealFragmentArgs.fromBundle(getArguments()).getMealName();
        mealPresenter=new MealPresenter(this, Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        mealPresenter.getMealItem(mealNameItem);
        layoutManager=new GridLayoutManager(getContext(),3);

        return view;
    }

    @Override
    public void showMeal(List<Meal> meal) {
        if(FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().isAnonymous()){
            addToFav_btn.setVisibility(View.INVISIBLE);
            addToPlane_btn.setVisibility(View.INVISIBLE);
        }
        mealName.setText(meal.get(0).getStrMeal());
        mealSteps.setText(meal.get(0).getStrInstructions());
        Glide.with(this)
                .load(meal.get(0).getStrMealThumb()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView);
        if(meal.get(0).getStrIngredient1()!="")
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient1(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient1()+".png"));
        if(!meal.get(0).getStrIngredient2().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient2(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient2()+".png"));
        if(!meal.get(0).getStrIngredient3().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient3(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient3()+".png"));
        if(!meal.get(0).getStrIngredient4().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient4(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient4()+".png"));
        if(!meal.get(0).getStrIngredient5().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient5(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient5()+".png"));
        if(!meal.get(0).getStrIngredient6().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient6(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient6()+".png"));
        if(!meal.get(0).getStrIngredient7().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient7(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient7()+".png"));
        if(!meal.get(0).getStrIngredient8().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient8(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient8()+".png"));
        if(!meal.get(0).getStrIngredient9().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient9(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient9()+".png"));
        if(!meal.get(0).getStrIngredient10().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient10(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient10()+".png"));
        if(!meal.get(0).getStrIngredient11().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient11(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient11()+".png"));
        if(!meal.get(0).getStrIngredient12().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient12(),"https://www.themealdb.com/images/ingredients/"+ meal.get(0).getStrIngredient12()+".png"));
        ingredientAdapter=new IngredientAdapter(getContext(),ingredients);
        ingredientAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(ingredientAdapter);
        recyclerView.setLayoutManager(layoutManager);

        if(!meal.get(0).getStrYoutube().equals(""))
        {
            videoSplit =meal.get(0).getStrYoutube().split("=");
            videoId =videoSplit[1];
        }else{
            videoId =" ";
        }
        getLifecycle().addObserver(videoView);

        videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        addToFav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFav_btn.setIconTint(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.red)));
                Meal my_meal = meal.get(0);
                if(!my_meal.isFavorite()){
                    my_meal.setFavorite(true);
                    mealPresenter.addToFavorite(my_meal);
                    mealPresenter.uploadMeal(my_meal);
                }
            }
        });
        addToPlane_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] days = {"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("     Select Day   ");
                builder.setSingleChoiceItems(days, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedDay = days[which];
                        Meal my_meal = meal.get(0);
                        my_meal.setNameDay(selectedDay);
                        mealPresenter.addToFavorite(my_meal);
                        mealPresenter.uploadPlan(my_meal);

                    }

                });
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();


            }
        });
    }





    /*@Override
    public void addMealToPlan(Meal Meal) {
        mealPresenter.addToPlan(Meal);

    }*/


}