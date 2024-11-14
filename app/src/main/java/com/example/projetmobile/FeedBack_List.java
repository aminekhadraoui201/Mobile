package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    //fl blayes lkol
    @Override
    public boolean onCreateOptionsMenu (Menu menu ){

        MenuInflater menuInflater= new MenuInflater(this);
        menuInflater.inflate(R.menu.mpd,menu);

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profil) {
            // Handle "Profil" menu item click
            Intent profileIntent = new Intent(FeedBack_List.this, ProfileActivity.class);
            startActivity(profileIntent);
            return true;
        } else if (item.getItemId() == R.id.deco) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(FeedBack_List.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }
        else if (item.getItemId() == R.id.Donner) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(FeedBack_List.this, ListActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }
        else if (item.getItemId() == R.id.hopito) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(FeedBack_List.this, ListHActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }
        else if (item.getItemId() == R.id.feedbak) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(FeedBack_List.this, FeedBack_List.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    }