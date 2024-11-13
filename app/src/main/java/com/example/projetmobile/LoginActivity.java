package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
                    // Récupérer tous les utilisateurs depuis la base de données
                    List<User> users = appDataBase.userDao().getAll();

                    // Vérification si l'email et le mot de passe correspondent à un utilisateur
                    for (User user : users) {
                        // Vérification pour l'admin
                        if (email.equals("aminekhadraoui51@gmail.com") && password.equals("000000*")) {
                            // Si l'email et le mot de passe sont "admin", on redirige vers ListActivity
                            Intent intent = new Intent(LoginActivity.this, Admin.class);
                            startActivity(intent);
                            finish(); // Fermer LoginActivity
                            return;
                        }

                        // Vérification pour les autres utilisateurs
                        if (user.getEmail().equals(email) && user.getMot_pass().equals(password)) {
                            // L'utilisateur est valide, on redirige vers ProfileActivity
                            String r=user.getRole();
                            if (r.equals("Hopital")){ Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                Log.e("test", "Updated User: ID=" + user.getUid() + ", Name=" + user.getNom());

                                intent.putExtra("user_id", user.getUid());
                                intent.putExtra("user_nom", user.getNom());
                                intent.putExtra("user_prenom", user.getPrenom());
                                intent.putExtra("user_email", user.getEmail());
                                intent.putExtra("user_datnaiss", user.getDatnaiss());
                                intent.putExtra("user_Mot_pass", user.getMot_pass());
                                intent.putExtra("user_lieu", user.getLieu());
                                startActivity(intent);
                                finish(); // Fermer LoginActivity
                                return;}else{ Intent intent = new Intent(LoginActivity.this, HopitalActivity.class);
                                Log.e("test", "Updated User: ID=" + user.getUid() + ", Name=" + user.getNom());

                                intent.putExtra("user_id", user.getUid());
                                intent.putExtra("user_nom", user.getNom());
                                intent.putExtra("user_prenom", user.getPrenom());
                                intent.putExtra("user_email", user.getEmail());
                                intent.putExtra("user_datnaiss", user.getDatnaiss());
                                intent.putExtra("user_Mot_pass", user.getMot_pass());
                                intent.putExtra("user_lieu", user.getLieu());
                                startActivity(intent);
                                finish(); // Fermer LoginActivity
                                return;}





                        }
                    }

                    // Si aucun utilisateur n'est trouvé avec ces identifiants
                    errorText.setText("Email ou mot de passe incorrect.");
                }
            }

        });
    }
}
