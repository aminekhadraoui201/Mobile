package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

        // Set up the window insets listener to handle system bar padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.d);
        recyclerViewh= findViewById(R.id.h);


        // Set up LinearLayoutManager for the RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManagerh = new LinearLayoutManager(this);
        linearLayoutManagerh.setReverseLayout(true);
        linearLayoutManagerh.setStackFromEnd(true);
        recyclerViewh.setLayoutManager(linearLayoutManagerh);

        // Initialize the AppDataBase instance
        appDataBase = AppDataBase.getinstance(this);

        // Fetch users from the database and set the adapter
        List<User> users = appDataBase.userDao().getAllDonneur(); // Directly fetch the list
        adapterDonneur = new AdapterDonneur(ListActivity.this, users, appDataBase);
        recyclerView.setAdapter(adapterDonneur);
        List<User> usersh = appDataBase.userDao().getAllHopital(); // Directly fetch the list
        adapterHopital = new AdapterDonneur(ListActivity.this, usersh, appDataBase);
        recyclerViewh.setAdapter(adapterHopital);

    }
}
