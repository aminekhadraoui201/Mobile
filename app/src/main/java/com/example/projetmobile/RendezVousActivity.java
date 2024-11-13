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
import com.example.projetmobile.Entite.RendezVous;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RendezVousActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RendezVousAdapter adapter;
    private List<RendezVous> rendezVousList;

    private EditText fullNameEditText, cityEditText, hospitalNameEditText, dateEditText;
    private Button addButton;

    private AppDataBase db;
    private RendezVousDao rendezVousDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendezvous);

        // Initialize UI elements
        recyclerView = findViewById(R.id.recyclerView);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        cityEditText = findViewById(R.id.cityEditText);
        hospitalNameEditText = findViewById(R.id.hospitalNameEditText);
        dateEditText = findViewById(R.id.dateEditText);
        addButton = findViewById(R.id.addButton);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the database and DAO
        db = AppDataBase.getinstance(this);
        rendezVousDao = db.rendezVousDao();

        // Initialize the adapter and set it to the RecyclerView
        rendezVousList = new ArrayList<>();
        adapter = new RendezVousAdapter(rendezVousList);
        recyclerView.setAdapter(adapter);

        // Load the RendezVous data
        loadRendezVousData();

        // Set listener for the add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewRendezVous();
            }
        });
    }

    private void loadRendezVousData() {
        // Fetch the data from the database
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

    private void addNewRendezVous() {
        // Get input from the EditText fields
        String fullName = fullNameEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String hospitalName = hospitalNameEditText.getText().toString();
        String dateString = dateEditText.getText().toString();

        if (fullName.isEmpty() || city.isEmpty() || hospitalName.isEmpty() || dateString.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert the date string to LocalDateTime
        LocalDateTime date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDateTime.parse(dateString);
        }

        // Create a new RendezVous object
        RendezVous newRendezVous = new RendezVous(fullName, city, hospitalName, date);

        // Insert the new RendezVous into the database
        new Thread(new Runnable() {
            @Override
            public void run() {
                rendezVousDao.insert(newRendezVous);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadRendezVousData();
                        Toast.makeText(RendezVousActivity.this, "RendezVous added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}
