package com.example.cookingproject.profile.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookingproject.HomeActivity;
import com.example.cookingproject.LoginActivity;
import com.example.cookingproject.Model.Repository;
import com.example.cookingproject.Model.UserData;
import com.example.cookingproject.Network.MealClient;
import com.example.cookingproject.R;
import com.example.cookingproject.localdatabase.ConcreteLocalSource;
import com.example.cookingproject.profile.presenter.ProfilePresenter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class ProfileFragment extends Fragment {
    TextView nameTextView;
    LinearLayout logoutLinear;
    LinearLayout favouriteLinear;
    LinearLayout planLinear;
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    ProfilePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        nameTextView = view.findViewById(R.id.nameTxt);
        logoutLinear = view.findViewById(R.id.logout_linear);
        favouriteLinear = view.findViewById(R.id.favorite_linear);
        planLinear = view.findViewById(R.id.plan_linear);
        presenter = new ProfilePresenter(Repository.getInstance(ConcreteLocalSource.getInstance(container.getContext()), MealClient.getInstance()));
        getUserData();
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        ((HomeActivity) requireActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favouriteLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(ProfileFragmentDirections.actionProfileFragmentToFavoriteFragment());


            }
        });
        planLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(ProfileFragmentDirections.actionProfileFragmentToPlanFragment());


            }
        });

        logoutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                signOut();
                presenter.clear();


            }
        });
    }

    public void signOut() {
        mAuth.signOut();
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    public void getUserData() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
            nameTextView.setText("Guest");
        }
        else if (FirebaseAuth.getInstance().getCurrentUser() != null && !FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
            if(FirebaseAuth.getInstance().getCurrentUser().getProviderData().get(1).getProviderId().equals("google.com")){
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
                String displayName = account.getDisplayName();
                nameTextView.setText(displayName);
            } else {
                firebaseFirestore.collection("Users")
                        .document(mAuth.getCurrentUser().getUid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    UserData userData = task.getResult().toObject(UserData.class);
                                    System.out.println(userData.getUsername());
                                    nameTextView.setText(userData.getUsername());

                                } else {
                                    String errorMessage = Objects.requireNonNull(task.getException()).getLocalizedMessage();
                                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }
}