package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.User;

public class HopitalActivity extends AppCompatActivity {
    private EditText nom, email, pass, lieu, editTextDate;
    private Button logoutButton, editButton,ldon,don;
    private AppDataBase appDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hopital);
        // Initialiser les champs EditText
        nom = findViewById(R.id.nom);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        lieu = findViewById(R.id.lieu);

        // Initialiser le bouton de déconnexion et de mise à jour
        logoutButton = findViewById(R.id.logoutButton);
        editButton = findViewById(R.id.editButton);
        don = findViewById(R.id.adddon);
        ldon = findViewById(R.id.listDon);

        // Récupérer les données envoyées via l'Intent
        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id",0);
        String userName = intent.getStringExtra("user_nom");
        String userPrenom = intent.getStringExtra("user_prenom");
        String userEmail = intent.getStringExtra("user_email");
        String userDateNaiss = intent.getStringExtra("user_datnaiss");
        String userPassword = intent.getStringExtra("user_Mot_pass");
        String userLieu = intent.getStringExtra("user_lieu");

        // Remplir les champs avec les données reçues
        nom.setText(userName);
        email.setText(userEmail);
        pass.setText(userPassword);
        lieu.setText(userLieu);
        editTextDate.setText(userDateNaiss);
        // Initialiser l'instance de la base de données
        appDataBase = AppDataBase.getinstance(this);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Gestion du bouton de déconnexion
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retour à LoginActivity
                Intent intent = new Intent(HopitalActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Fermer l'activité de profil
            }
        });
        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retour à LoginActivity
                Intent intent = new Intent(HopitalActivity.this, AddOrEditRequestActivity.class);
                startActivity(intent);
                finish(); // Fermer l'activité de profil
            }
        });
        ldon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retour à LoginActivity
                Intent intent = new Intent(HopitalActivity.this, ViewRequestsActivity.class);
                startActivity(intent);
                finish(); // Fermer l'activité de profil
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les données modifiées
                String updatedNom = nom.getText().toString();
                String updatedEmail = email.getText().toString();
                String updatedPassword = pass.getText().toString();
                String updatedLieu = lieu.getText().toString();
                String updatedDateNaiss = editTextDate.getText().toString();

                // Valider les champs avant de mettre à jour
                if (updatedNom.isEmpty()  || updatedEmail.isEmpty() || updatedPassword.isEmpty()) {
                    Toast.makeText(HopitalActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérification de l'email (format simple)
                if (!isValidEmail(updatedEmail)) {
                    Toast.makeText(HopitalActivity.this, "Veuillez entrer un email valide.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérification du mot de passe (longueur minimale de 6 caractères)
                if (updatedPassword.length() < 6) {
                    Toast.makeText(HopitalActivity.this, "Le mot de passe doit comporter au moins 6 caractères.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer l'objet utilisateur mis à jour
                User updatedUser = new User(userId, updatedNom,"",  updatedEmail, updatedPassword, updatedDateNaiss, "", updatedLieu);

                try {
                    // Tentative de mise à jour de l'utilisateur dans la base de données
                    int rowsUpdated = appDataBase.userDao().updateUser(updatedUser);  // Met à jour l'utilisateur
                    Log.e("test", "Updated User: ID=" + userId + ", Name=" + updatedNom  + ", Email=" + updatedEmail + ", DateNaiss=" + updatedDateNaiss + ", Lieu=" + updatedLieu);
                    // Vérifier si la mise à jour a réussi
                    if (rowsUpdated > 0) {
                        // Afficher un message de confirmation
                        Toast.makeText(HopitalActivity.this, "Informations mises à jour avec succès.", Toast.LENGTH_SHORT).show();

                        // Retourner à l'écran précédent
                        Intent intent = new Intent(HopitalActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Fermer l'activité de profil
                    } else {
                        // Si aucune ligne n'a été affectée
                        Toast.makeText(HopitalActivity.this, "Aucune information mise à jour.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    // Si une exception survient pendant la mise à jour
                    Toast.makeText(HopitalActivity.this, "Erreur lors de la mise à jour : " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace(); // Affiche l'exception dans les logs pour débogage
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Fonction pour vérifier le format de l'email
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}