package com.example.cookingproject.meals.view;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cookingproject.Model.Ingredient;
import com.example.cookingproject.Model.Meal;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.Network.RemoteSource;
import com.example.cookingproject.R;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;
import com.example.cookingproject.meals.presenter.MealPresenter;
import com.google.android.material.button.MaterialButton;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MealFragment extends Fragment implements mealViewInterface{

    TextView mealName,mealCountry,mealSteps;
    RecyclerView recyclerView;
   ArrayList<Ingredient> ingredients = new ArrayList<>();
    IngredientAdapter ingredientAdapter;
    GridLayoutManager layoutManager;
    ImageButton addToPlane_btn;
    MaterialButton addToFav_btn;
    ImageView imageView;
    MealPresenter mealPresenter;

    YouTubePlayerView videoView ;

    String[] videoSplit;
    String videoId;
    String []days;

    mealViewInterface mealViewInterface;
    boolean[] checkedDays;
    List<String> selectedDays;
    String mealNameItem;

    public MealFragment() {
        // Required empty public constructor
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
       // addToPlane_btn=view.findViewById(R.id.add_to_calender);
        imageView=view.findViewById(R.id.profileUserImage);

        String mealNameItem = MealFragmentArgs.fromBundle(getArguments()).getMealName();
        mealPresenter=new MealPresenter(this, Repository.getInstance(getContext(), ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        mealPresenter.getMealItem(mealNameItem);
        layoutManager=new GridLayoutManager(getContext(),3);

        return view;
    }

    @Override
    public void showMeal(List<Meal> meal) {
        mealName.setText(meal.get(0).getStrMeal());
        mealSteps.setText(meal.get(0).getStrInstructions());
        Glide.with(this)
                .load(meal.get(0).getStrMealThumb()).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView);
        if(meal.get(0).getStrIngredient1()!="")
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient1(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient1()+".png"));
        if(!meal.get(0).getStrIngredient2().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient2(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient2()+".png"));
        if(!meal.get(0).getStrIngredient3().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient3(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient3()+".png"));
        if(!meal.get(0).getStrIngredient4().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient4(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient4()+".png"));
        if(!meal.get(0).getStrIngredient5().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient5(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient5()+".png"));
        if(!meal.get(0).getStrIngredient6().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient6(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient6()+".png"));
        if(!meal.get(0).getStrIngredient7().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient7(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient7()+".png"));
        if(!meal.get(0).getStrIngredient8().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient8(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient8()+".png"));
        if(!meal.get(0).getStrIngredient9().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient9(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient9()+".png"));
        if(!meal.get(0).getStrIngredient10().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient10(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient10()+".png"));
        if(!meal.get(0).getStrIngredient11().equals(""))
            ingredients.add(new Ingredient(meal.get(0).getStrIngredient11(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient11()+".png"));
        if(!meal.get(0).getStrIngredient12().equals(""))
           ingredients.add(new Ingredient(meal.get(0).getStrIngredient12(),"https://www.themealdb.com/images/ingredients/"+meal.get(0).getStrIngredient12()+".png"));
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
                }
            }
        });

    }




    /*@Override
    public void addMealToPlan(Meal Meal) {
        mealPresenter.addToPlan(Meal);

    }*/


}