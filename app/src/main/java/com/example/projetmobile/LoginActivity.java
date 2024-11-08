package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private TextView errorText;
    private AppDataBase appDataBase; // Declare AppDataBase instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        errorText = findViewById(R.id.errorText);
        Button loginButton = findViewById(R.id.loginButton);

        // Initialize the database instance
        appDataBase = AppDataBase.getinstance(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                // Validation simple
                if (email.isEmpty() || password.isEmpty()) {
                    errorText.setText("Veuillez remplir tous les champs.");
                } else {
                    // Query the database for the user with the given email and password
                    List<User> users = appDataBase.userDao().getAll(); // Get all users from the database
                    boolean isValidUser = false;

                    // Check if a user with the given email and password exists
                    for (User user : users) {
                        if (user.getEmail().equals("aminekhadraoui51@gmail.com")) {
                            Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                            startActivity(intent);
                        } else {
                            // Navigate to ProfileActivity for other users
                            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        }
                        finish(); // Close the LoginActivity
                        break;
                    }

                    if (isValidUser) {
                        // If valid, navigate to ListActivity
                        Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                        startActivity(intent);
                        finish(); // Close the LoginActivity
                    } else {
                        // If invalid, show error message
                        errorText.setText("Email ou mot de passe incorrect.");
                    }
                }
            }
        });
    }
}
