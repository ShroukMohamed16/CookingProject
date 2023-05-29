package com.example.cookingproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
   MaterialButton btn;
    private FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;

   /*




        // Get current user ID
        String userId = mAuth.getCurrentUser().getUid();

        // Get user data from database
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get user object from snapshot
                User user = dataSnapshot.getValue(User.class);

                // Set text of TextViews to display user data
                mNameTextView.setText(user.getName());
                mEmailTextView.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle databaseerrors
            }
        });

        return view;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        btn = view.findViewById(R.id.logout_btn);
        nameTextView = view.findViewById(R.id.nameTxt);
        firebaseFirestore.collection("Users")
                .document(mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            UserData userData=task.getResult().toObject(UserData.class);
                            System.out.println(userData.getUsername());
                            nameTextView.setText(userData.getUsername());

                        }else{
                            String errorMessage= Objects.requireNonNull(task.getException()).getLocalizedMessage();
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn.setOnClickListener(new View.OnClickListener() {
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


}