package com.example.cookingproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    TextInputLayout emailTextInputEditText, usernameTextInputEditText, passwordTextInputEditText, confirmPasswordTextInputEditText;
    MaterialButton signUpButton;
    String email, username;
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailTextInputEditText = findViewById(R.id.register_emailTextField);
        usernameTextInputEditText = findViewById(R.id.register_username);
        passwordTextInputEditText = findViewById(R.id.register_passwordTextField);
        confirmPasswordTextInputEditText = findViewById(R.id.register_confirm_passwordTextField);
        signUpButton = findViewById(R.id.btn_register);

        signUpButton.setOnClickListener(v -> {
            email = Objects.requireNonNull(emailTextInputEditText.getEditText()).getText().toString().trim();
            username = Objects.requireNonNull(usernameTextInputEditText.getEditText()).getText().toString().trim();
            String password = Objects.requireNonNull(passwordTextInputEditText.getEditText()).getText().toString().trim();
            String confirmPassword = Objects.requireNonNull(confirmPasswordTextInputEditText.getEditText()).getText().toString().trim();

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Please Fill All Data", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmPassword)) {
                Toast.makeText(RegisterActivity.this, "Passwords Not Matching", Toast.LENGTH_SHORT).show();
                return;

            }
            if (password.length() < 6) {
                Toast.makeText(RegisterActivity.this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;

            }
            createUserByEmail(email, password);
        });


    }

    private void createUserByEmail(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                         uploadUserDate();

                    } else {
                        String errorMsg = Objects.requireNonNull(task.getException()).getLocalizedMessage();
                        Toast.makeText(RegisterActivity.this, "Error:" + errorMsg, Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void uploadUserDate() {
        UserData userData = new UserData(email, username);
        firebaseFirestore.collection("Users")
                .document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid())
                .set(userData).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                    } else {
                        String errorMessage = Objects.requireNonNull(task.getException()).getLocalizedMessage();
                        System.out.println(errorMessage);
                        Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}