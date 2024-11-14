package com.example.projetmobile;

import android.annotation.SuppressLint;
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
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.User;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView,recyclerViewh;
    private AdapterDonneur adapterDonneur,adapterHopital;
    private AppDataBase appDataBase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Set up the window insets listener to handle system bar padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.d);


        // Set up LinearLayoutManager for the RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        // Initialize the AppDataBase instance
        appDataBase = AppDataBase.getinstance(this);

        // Fetch users from the database and set the adapter
        List<User> users = appDataBase.userDao().getAllDonneur(); // Directly fetch the list
        adapterDonneur = new AdapterDonneur(ListActivity.this, users, appDataBase);
        recyclerView.setAdapter(adapterDonneur);

        Button retrn = findViewById(R.id.retrn);

        // OnClickListener pour le bouton Liste Des Rendez-vous
        retrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacer par l'intention vers l'activité correspondante
                Intent intent = new Intent(ListActivity.this, Admin.class);
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
            Intent profileIntent = new Intent(ListActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            return true;
        } else if (item.getItemId() == R.id.deco) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ListActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }
        else if (item.getItemId() == R.id.Donner) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ListActivity.this, ListActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }
        else if (item.getItemId() == R.id.hopito) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ListActivity.this, ListHActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }
        else if (item.getItemId() == R.id.feedbak) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ListActivity.this, FeedBack_List.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
