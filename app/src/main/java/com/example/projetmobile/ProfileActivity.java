package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.User;

public class ProfileActivity extends AppCompatActivity {
    private EditText nom, prenom, email, pass, cpass, lieu, editTextDate;
    private Button logoutButton, editButton,rdv;
    private AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        // Initialiser les champs EditText
        nom = findViewById(R.id.nomH);
        prenom = findViewById(R.id.prenom);
        email = findViewById(R.id.emailH);
        pass = findViewById(R.id.passH);
        cpass = findViewById(R.id.cpass);
        lieu = findViewById(R.id.lieuH);
        editTextDate = findViewById(R.id.editTextDate);

        // Initialiser le bouton de déconnexion et de mise à jour

        editButton = findViewById(R.id.editButtonH);
        rdv =findViewById(R.id.rdv);

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
        prenom.setText(userPrenom);
        email.setText(userEmail);
        pass.setText(userPassword);
        cpass.setText(userPassword);  // Assumer que le mot de passe est le même pour la confirmation
        lieu.setText(userLieu);
        editTextDate.setText(userDateNaiss);  // La date de naissance reçue

        // Initialiser l'instance de la base de données
        appDataBase = AppDataBase.getinstance(this);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les données modifiées
                String updatedNom = nom.getText().toString();
                String updatedPrenom = prenom.getText().toString();
                String updatedEmail = email.getText().toString();
                String updatedPassword = pass.getText().toString();
                String updatedLieu = lieu.getText().toString();
                String updatedDateNaiss = editTextDate.getText().toString();

                // Valider les champs avant de mettre à jour
                if (updatedNom.isEmpty() || updatedPrenom.isEmpty() || updatedEmail.isEmpty() || updatedPassword.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérification de l'email (format simple)
                if (!isValidEmail(updatedEmail)) {
                    Toast.makeText(ProfileActivity.this, "Veuillez entrer un email valide.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérification du mot de passe (longueur minimale de 6 caractères)
                if (updatedPassword.length() < 6) {
                    Toast.makeText(ProfileActivity.this, "Le mot de passe doit comporter au moins 6 caractères.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer l'objet utilisateur mis à jour
                User updatedUser = new User(userId, updatedNom, updatedPrenom, updatedEmail, updatedPassword, updatedDateNaiss, "", updatedLieu);

                try {
                    // Tentative de mise à jour de l'utilisateur dans la base de données
                    int rowsUpdated = appDataBase.userDao().updateUser(updatedUser);  // Met à jour l'utilisateur
                    Log.e("test", "Updated User: ID=" + userId + ", Name=" + updatedNom + ", Prenom=" + updatedPrenom + ", Email=" + updatedEmail + ", DateNaiss=" + updatedDateNaiss + ", Lieu=" + updatedLieu);
                    // Vérifier si la mise à jour a réussi
                    if (rowsUpdated > 0) {
                        // Afficher un message de confirmation
                        Toast.makeText(ProfileActivity.this, "Informations mises à jour avec succès.", Toast.LENGTH_SHORT).show();

                        // Retourner à l'écran précédent
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Fermer l'activité de profil
                    } else {
                        // Si aucune ligne n'a été affectée
                        Toast.makeText(ProfileActivity.this, "Aucune information mise à jour.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    // Si une exception survient pendant la mise à jour
                    Toast.makeText(ProfileActivity.this, "Erreur lors de la mise à jour : " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace(); // Affiche l'exception dans les logs pour débogage
                }
            }
        });


    }

    // Inflater le menu si nécessaire
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menudonneur, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profil) {
            // Lors de la navigation vers une autre activité (par exemple, depuis HopitalActivity)
            Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();  // Ferme l'activité actuelle
            return true;
        } else if (item.getItemId() == R.id.deco) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // Fonction pour vérifier le format de l'email
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
