package com.example.projetmobile;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Entite.DossierMedicale;
import java.util.ArrayList;
import java.util.List;

public class DossierMedicaleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DossierMedicaleAdapter adapter;
    private List<DossierMedicale> dossierList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_dossier_medicale);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize dossierList as an empty ArrayList to prevent null reference issues
        dossierList = new ArrayList<>();

        // Initialize adapter with the dossierList
        adapter = new DossierMedicaleAdapter(dossierList);
        recyclerView.setAdapter(adapter);

        // Load data into dossierList (could be from a database, API, etc.)
        loadDossierMedicaleData();
    }

    private void loadDossierMedicaleData() {
        // This is where you'd load data from your database, API, or other data source.
        // Here’s a sample way to add mock data for testing purposes:

        dossierList.add(new DossierMedicale(1, "John", "Doe", "123 Main St", "O+", "555-1234"));
        dossierList.add(new DossierMedicale(2, "Jane", "Smith", "456 Oak Ave", "A-", "555-5678"));

        // Notify the adapter that the data has changed so it refreshes the RecyclerView
        adapter.notifyDataSetChanged();
    }
}
