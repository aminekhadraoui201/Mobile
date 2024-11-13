package com.example.projetmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Entite.RendezVous;
import com.example.projetmobile.R;

import java.util.ArrayList;
import java.util.List;

public class RendezVousActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RendezVousAdapter adapter;
    private List<RendezVous> rendezVousList;

    private EditText fullNameEditText, noteEditText, typeRendezVousEditText, locationEditText, dateEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the list and adapter
        rendezVousList = new ArrayList<>();
        adapter = new RendezVousAdapter(rendezVousList);
        recyclerView.setAdapter(adapter);

        // Initialize input fields
        fullNameEditText = findViewById(R.id.fullNameEditText);
        noteEditText = findViewById(R.id.noteEditText);
        typeRendezVousEditText = findViewById(R.id.typeRendezVousEditText);
        locationEditText = findViewById(R.id.locationEditText);
        dateEditText = findViewById(R.id.dateEditText);
        addButton = findViewById(R.id.addButton);

        // Set up the button to add new RendezVous
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRendezVous();
            }
        });
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

            // Add the new RendezVous to the list
            rendezVousList.add(newRendezVous);

            // Notify the adapter to update the RecyclerView
            adapter.notifyItemInserted(rendezVousList.size() - 1);

            // Clear the input fields
            fullNameEditText.setText("");
            noteEditText.setText("");
            typeRendezVousEditText.setText("");
            locationEditText.setText("");
            dateEditText.setText("");
        }
    }
}
