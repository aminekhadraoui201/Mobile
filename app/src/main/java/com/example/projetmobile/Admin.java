package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Récupération des boutons par leur ID
        Button btnListeRendezVous = findViewById(R.id.btnListeRendezVous);
        Button btnListeDonneurs = findViewById(R.id.btnListeDonneurs);
        Button btnListeHopital = findViewById(R.id.btnListeHopital);
        Button btnListeFeedback = findViewById(R.id.btnListeFeedback);

        // OnClickListener pour le bouton Liste Des Rendez-vous
        btnListeRendezVous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacer par l'intention vers l'activité correspondante
                Intent intent = new Intent(Admin.this, ViewRequestsActivity.class);
                startActivity(intent);
            }
        });
        // OnClickListener pour le bouton Liste Des Donneurs
        btnListeDonneurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacer par l'intention vers l'activité correspondante
                Intent intent = new Intent(Admin.this, ListActivity.class);
                startActivity(intent);
            }
        });

        // OnClickListener pour le bouton Liste Des Hôpitaux
        btnListeHopital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacer par l'intention vers l'activité correspondante
                Intent intent = new Intent(Admin.this, ListHActivity.class);
                startActivity(intent);
            }
        });
        // OnClickListener pour le bouton Liste Des Feedback
        btnListeFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacer par l'intention vers l'activité correspondante
                Intent intent = new Intent(Admin.this, FeedBack_List.class);
                startActivity(intent);
            }
        });



    }
}