package com.example.projetmobile;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile.RendezVousAdapter;
import com.example.projetmobile.Entite.RendezVous;

import java.time.LocalDateTime;
import java.util.List;

public class RendezVousActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RendezVousAdapter adapter;
    private List<RendezVous> rendezVousList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_rendezvous);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter with empty list
        adapter = new RendezVousAdapter(rendezVousList);
        recyclerView.setAdapter(adapter);

        // Load data into rendezVousList (this could be from your database)
        loadRendezVousData();
    }

    private void loadRendezVousData() {
        // Example: Load data from a database (via RendezVousDao)
        // Here, we just mock data for testing purposes

        // Example mock data
        rendezVousList.add(new RendezVous("John Doe", "New York", "City Hospital", LocalDateTime.now()));
        rendezVousList.add(new RendezVous("Jane Smith", "Los Angeles", "General Hospital", LocalDateTime.now()));

        // Notify the adapter that the data has changed
        adapter.setRendezVousList(rendezVousList);
    }
}
