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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.FeedBack;


import java.util.List;

public class FeedBack_List extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AppDataBase appDataBase;
    private AdapterFeedBack adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed_back_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.feedbacklist);

        // Set up LinearLayoutManager for the RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Initialize the AppDataBase instance
        appDataBase = AppDataBase.getinstance(this);

        // Récupérer les feedbacks depuis la base de données et définir l'adaptateur
        List<FeedBack> feedBacks = appDataBase.feedBackDao().getAllFeedBacks(); // Récupérer tous les feedbacks
        adapter = new AdapterFeedBack(FeedBack_List.this, feedBacks, appDataBase); // Adapter pour les feedbacks
        recyclerView.setAdapter(adapter);

        Button retrn = findViewById(R.id.retrn);

        // OnClickListener pour le bouton Liste Des Rendez-vous
        retrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacer par l'intention vers l'activité correspondante
                Intent intent = new Intent(FeedBack_List.this, Admin.class);
                startActivity(intent);
            }
        });
    }
}
