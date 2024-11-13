package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        errorText = findViewById(R.id.errorText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                // Validation simple
                if (email.isEmpty() || password.isEmpty()) {
                    errorText.setText("Veuillez remplir tous les champs.");
                } else {
                    // Naviguer vers ProfileActivity
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish(); // Fermer l'activité de login
                }
            }
        });
    }
}
