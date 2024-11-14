package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.User;

import java.util.List;
import java.util.concurrent.Executor;

public class LoginActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private TextView errorText;
    private AppDataBase appDataBase;

    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

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

        // Setup BiometricPrompt
        Executor executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                // Si l'authentification est réussie, on passe à l'écran suivant
                authenticateUser();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                // Si l'authentification échoue
                Toast.makeText(LoginActivity.this, "Authentification échouée", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Authentification par empreinte digitale")
                .setSubtitle("Utilisez votre empreinte digitale pour vous connecter")
                .setNegativeButtonText("Annuler")
                .build();

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            // Validation simple
            if (email.isEmpty() || password.isEmpty()) {
                errorText.setText("Veuillez remplir tous les champs.");
            } else {
                // Vérifier la disponibilité de la biométrie
                BiometricPrompt biometricPrompt = new BiometricPrompt(LoginActivity.this, ContextCompat.getMainExecutor(this), new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        // Si l'authentification biométrique réussie, procéder à la connexion
                        authenticateUser();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        // Si l'authentification échoue
                        Toast.makeText(LoginActivity.this, "Échec de l'authentification", Toast.LENGTH_SHORT).show();
                    }
                });

                biometricPrompt.authenticate(promptInfo);  // Affiche le prompt biométrique
            }
        });
    }

    // Fonction pour authentifier l'utilisateur via email et mot de passe
    private void authenticateUser() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        // Récupérer tous les utilisateurs depuis la base de données
        List<User> users = appDataBase.userDao().getAll();

        // Vérification si l'email et le mot de passe correspondent à un utilisateur
        for (User user : users) {
            if (email.equals(user.getEmail()) && password.equals(user.getMot_pass())) {
                // L'utilisateur est valide, redirige vers l'écran approprié
                String r = user.getRole();
                Intent intent;

                if (r.equals("Donneur")) {
                    intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra("user_id", user.getUid());
                    intent.putExtra("user_nom", user.getNom());
                    intent.putExtra("user_prenom", user.getPrenom());
                    intent.putExtra("user_email", user.getEmail());
                    intent.putExtra("user_datnaiss", user.getDatnaiss());
                    intent.putExtra("user_Mot_pass", user.getMot_pass());
                    intent.putExtra("user_lieu", user.getLieu());
                    startActivity(intent);
                    finish();
                } else {
                    intent = new Intent(LoginActivity.this, HopitalActivity.class);
                    intent.putExtra("user_id", user.getUid());
                    intent.putExtra("user_nom", user.getNom());
                    intent.putExtra("user_prenom", user.getPrenom());
                    intent.putExtra("user_email", user.getEmail());
                    intent.putExtra("user_datnaiss", user.getDatnaiss());
                    intent.putExtra("user_Mot_pass", user.getMot_pass());
                    intent.putExtra("user_lieu", user.getLieu());
                    startActivity(intent);
                    finish();
                }
                return;
            }
        }
        // Si aucun utilisateur n'est trouvé avec ces identifiants
        errorText.setText("Email ou mot de passe incorrect.");
    }
}
