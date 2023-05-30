package com.example.cookingproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookingproject.Model.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class ProfileFragment extends Fragment {
    TextView nameTextView;
    LinearLayout logoutLinear;
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        nameTextView = view.findViewById(R.id.nameTxt);
        logoutLinear = view.findViewById(R.id.logout_linear);
        getUserData();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logoutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();

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