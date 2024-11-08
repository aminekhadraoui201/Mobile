package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameText;
    private TextView bloodGroupText;
    private TextView medicalHistoryText;
    private TextView lastDonationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        nameText = findViewById(R.id.nameText);
        bloodGroupText = findViewById(R.id.bloodGroupText);
        medicalHistoryText = findViewById(R.id.medicalHistoryText);
        lastDonationText = findViewById(R.id.lastDonationText);
        Button logoutButton = findViewById(R.id.logoutButton);

        // Remplir les informations du profil (statique pour l'exemple)
        nameText.setText("Nom: Jean Dupont");
        bloodGroupText.setText("Groupe Sanguin: A+");
        medicalHistoryText.setText("Antécédents Médicaux: Aucun");
        lastDonationText.setText("Dernier Don: 01/10/2024");

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retour à LoginActivity
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Fermer l'activité de profil
            }
        });
    }
}
