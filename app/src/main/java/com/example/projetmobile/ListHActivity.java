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
import com.example.projetmobile.Entite.User;

import java.util.List;

public class ListHActivity extends AppCompatActivity {
    private RecyclerView recyclerViewh;
    private AdapterDonneur adapterHopital;
    private AppDataBase appDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_hactivity);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerViewh= findViewById(R.id.h);
        LinearLayoutManager linearLayoutManagerh = new LinearLayoutManager(this);
        linearLayoutManagerh.setReverseLayout(true);
        linearLayoutManagerh.setStackFromEnd(true);
        recyclerViewh.setLayoutManager(linearLayoutManagerh);
        appDataBase = AppDataBase.getinstance(this);


        List<User> usersh = appDataBase.userDao().getAllHopital(); // Directly fetch the list
        adapterHopital = new AdapterDonneur(ListHActivity.this, usersh, appDataBase);
        recyclerViewh.setAdapter(adapterHopital);

        Button retrn = findViewById(R.id.retrn);

        // OnClickListener pour le bouton Liste Des Rendez-vous
        retrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacer par l'intention vers l'activité correspondante
                Intent intent = new Intent(ListHActivity.this, Admin.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu ){

        MenuInflater menuInflater= new MenuInflater(this);
        menuInflater.inflate(R.menu.mpd,menu);

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.profil) {
            // Handle "Profil" menu item click
            Intent profileIntent = new Intent(ListHActivity.this, ProfileActivity.class);
            startActivity(profileIntent);
            return true;
        } else if (item.getItemId() == R.id.deco) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ListHActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        } else if (item.getItemId() == R.id.Donner) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ListHActivity.this, ListActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        } else if (item.getItemId() == R.id.hopito) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ListHActivity.this, ListHActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        } else if (item.getItemId() == R.id.feedbak) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ListHActivity.this, FeedBack_List.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}