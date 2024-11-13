package com.example.projetmobile;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.DAO.RendezVousDao;
import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.RendezVous;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RendezVousActivity extends AppCompatActivity {

    private RendezVousAdapter adapter;
    private List<RendezVous> rendezVousList;

    private EditText fullNameEditText, cityEditText, hospitalNameEditText;
    private Button addButton;
    private DatePicker datePicker;

    private AppDataBase db;
    private RendezVousDao rendezVousDao;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendezvous);

        // Initialize UI elements
        fullNameEditText = findViewById(R.id.fullNameEditText);
        cityEditText = findViewById(R.id.cityEditText);
        hospitalNameEditText = findViewById(R.id.hospitalNameEditText);
        datePicker = findViewById(R.id.datePicker);
        addButton = findViewById(R.id.addButton);

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rendezVousList = new ArrayList<>();
        adapter = new RendezVousAdapter(rendezVousList);
        recyclerView.setAdapter(adapter);

        // Initialize the database and DAO
        db = AppDataBase.getinstance(this);
        rendezVousDao = db.rendezVousDao();

        // Load the RendezVous data
        loadRendezVousData();

        // Set listener for the add button
        addButton.setOnClickListener(v -> addNewRendezVous());
    }

    private void loadRendezVousData() {
        // Fetch the data from the database
        new Thread(() -> {
            List<RendezVous> loadedData = rendezVousDao.getAllRendezVous();
            runOnUiThread(() -> {
                rendezVousList.clear();
                rendezVousList.addAll(loadedData);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addNewRendezVous() {
        // Get input from the EditText fields
        String fullName = fullNameEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String hospitalName = hospitalNameEditText.getText().toString();

        if (fullName.isEmpty() || city.isEmpty() || hospitalName.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve the selected date from the DatePicker
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        LocalDateTime date = LocalDateTime.of(LocalDate.of(year, month + 1, day), LocalTime.MIDNIGHT);

        // Create a new RendezVous object
        RendezVous newRendezVous = new RendezVous(fullName, city, hospitalName, date);

        // Insert the new RendezVous into the database
        new Thread(() -> {
            rendezVousDao.insert(newRendezVous);
            runOnUiThread(() -> {
                loadRendezVousData();
                Toast.makeText(RendezVousActivity.this, "RendezVous added", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
}
