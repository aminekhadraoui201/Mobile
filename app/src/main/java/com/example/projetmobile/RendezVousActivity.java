package com.example.projetmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.DAO.RendezVousDao;
import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.DossierMedicale;
import com.example.projetmobile.Entite.RendezVous;
import com.example.projetmobile.R;

import java.util.ArrayList;
import java.util.List;

public class RendezVousActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RendezVousAdapter adapter;
    private List<RendezVous> rendezVousList;
    private AppDataBase db;
    private RendezVousDao rendezVousDao;

    private EditText fullNameEditText, noteEditText, typeRendezVousEditText, locationEditText, dateEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous);

        // Initialize UI elements
        recyclerView = findViewById(R.id.recyclerView);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        noteEditText = findViewById(R.id.noteEditText);
        typeRendezVousEditText = findViewById(R.id.typeRendezVousEditText);
        locationEditText = findViewById(R.id.locationEditText);
        dateEditText = findViewById(R.id.dateEditText);
        addButton = findViewById(R.id.addButton);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize database and DAO
        db = AppDataBase.getinstance(this);
        rendezVousDao = db.rendezVousDao();

        // Initialize the adapter and set it to the RecyclerView
        rendezVousList = new ArrayList<>();
        adapter = new RendezVousAdapter(rendezVousList);
        recyclerView.setAdapter(adapter);

        // Add button listener to insert new RendezVous
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRendezVous();
            }
        });

        // Load existing RendezVous data
        loadRendezVousData();
        ArrayList<Object> dossierList = new ArrayList<>();
        adapter = new RendezVousAdapter(rendezVousList);
        recyclerView.setAdapter(adapter);
    }

    private void loadRendezVousData() {
        // Fetch the data from the database asynchronously
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<RendezVous> loadedData = rendezVousDao.getAllRendezVous();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rendezVousList.clear();
                        rendezVousList.addAll(loadedData);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void addRendezVous() {
        String fullName = fullNameEditText.getText().toString().trim();
        String note = noteEditText.getText().toString().trim();
        String typeRendezVous = typeRendezVousEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();
        String date = dateEditText.getText().toString().trim();

        // Validate the inputs
        if (fullName.isEmpty() || note.isEmpty() || typeRendezVous.isEmpty() || location.isEmpty() || date.isEmpty()) {
            Toast.makeText(RendezVousActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Create a new RendezVous object
            RendezVous newRendezVous = new RendezVous(fullName, note, typeRendezVous, location, date);

            // Insert the new RendezVous into the database
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rendezVousDao.insertRendezVous(newRendezVous);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadRendezVousData();  // Reload the data to reflect the new entry
                            Toast.makeText(RendezVousActivity.this, "RendezVous added successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();

            // Clear the input fields
            fullNameEditText.setText("");
            noteEditText.setText("");
            typeRendezVousEditText.setText("");
            locationEditText.setText("");
            dateEditText.setText("");
        }
    }
}
