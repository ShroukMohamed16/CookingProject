package com.example.cookingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.cookingproject.favorite.view.FavoriteFragment;
import com.example.cookingproject.home.view.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.fragment.app.Fragment;


public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        replaceFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeFragment:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.searchFragment:
                        replaceFragment(new SearchFragment());
                        break;
                    case R.id.favoriteFragment:
                        replaceFragment(new FavoriteFragment());
                        break;
                    case R.id.planFragment:
                        replaceFragment(new PlanFragment());
                        break;
                    case R.id.profileFragment:
                        replaceFragment(new ProfileFragment());
                        break;



                }
                return true;
            }
        });


    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_layout,fragment)
                .commit();
    }


}