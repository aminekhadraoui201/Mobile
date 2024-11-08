package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        email = findViewById(R.id.email);
        motPass = findViewById(R.id.mot_pass);
        lieu = findViewById(R.id.lieu);
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

        // Basic validation
        if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create User object and add to database
        User user = new User(userName, userPrenom, userEmail, userPassword, dateOfBirth, userRole, userLieu);
        appDataBase.userDao().insertOne(user);

        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

        // Redirect to MainActivity or login screen
        startActivity(new Intent(RegistActivity.this, ListActivity.class));
        finish();
    }
}