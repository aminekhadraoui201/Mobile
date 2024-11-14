package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.User;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistActivity extends AppCompatActivity {


    private EditText nom, prenom, email, motPass, lieu;
    private Spinner roleSpinner;
    private DatePicker dateNaissance;
    private Button btnSignUp, btnSignIn;
    private AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Spinner mySpinner = findViewById(R.id.Spinner_role);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
///////////////////////
        nom = findViewById(R.id.nomH);
        prenom = findViewById(R.id.prenom);
        email = findViewById(R.id.emailH);
        motPass = findViewById(R.id.mot_pass);
        lieu = findViewById(R.id.lieuH);
        roleSpinner = findViewById(R.id.Spinner_role);
        dateNaissance = findViewById(R.id.datnaiss);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        appDataBase = AppDataBase.getinstance(this);

        // Set click listener for SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistActivity.this, ListActivity.class));
            }
        });

    }
    private void registerUser() {
        // Get data from input fields
        String userName = nom.getText().toString();
        String userPrenom = prenom.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = motPass.getText().toString();
        String userLieu = lieu.getText().toString();
        String userRole = roleSpinner.getSelectedItem().toString();

        int day = dateNaissance.getDayOfMonth();
        int month = dateNaissance.getMonth();
        int year = dateNaissance.getYear();
        String dateOfBirth = day + "/" + (month + 1) + "/" + year;

        // Validation des champs
        if (userName.isEmpty() || userPrenom.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || userLieu.isEmpty()) {
            Toast.makeText(this, "Tous les champs doivent être remplis.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validation de l'email (format simple)
        if (!isValidEmail(userEmail)) {
            Toast.makeText(this, "Veuillez entrer un email valide.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validation du mot de passe (longueur minimale de 6 caractères)
        if (userPassword.length() < 6) {
            Toast.makeText(this, "Le mot de passe doit comporter au moins 6 caractères.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validation de l'âge (l'utilisateur doit avoir au moins 18 ans)
        if (!isValidAge(year, month, day)) {
            Toast.makeText(this, "Vous devez avoir au moins 18 ans pour vous inscrire.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create User object and add to database
        User user = new User(userName, userPrenom, userEmail, userPassword, dateOfBirth, userRole, userLieu);
        appDataBase.userDao().insertOne(user);

        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

        // Redirect to MainActivity or login screen
        startActivity(new Intent(RegistActivity.this, LoginActivity.class));
        finish();
    }
    // Fonction pour valider le format de l'email
    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Fonction pour vérifier si l'utilisateur a au moins 18 ans
    private boolean isValidAge(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Mois est 0-indexé
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        int age = currentYear - year;

        // Si la personne n'a pas encore eu son anniversaire cette année, on réduit l'âge de 1
        if (currentMonth < (month + 1) || (currentMonth == (month + 1) && currentDay < day)) {
            age--;
        }

        return age >= 18;
    }
}