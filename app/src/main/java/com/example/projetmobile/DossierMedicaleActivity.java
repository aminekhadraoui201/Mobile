package com.example.projetmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.DossierMedicale;
import com.example.projetmobile.DAO.DossierMedicaleDao;

import java.util.ArrayList;
import java.util.List;

public class DossierMedicaleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DossierMedicaleAdapter adapter;
    private List<DossierMedicale> dossierList;
    private AppDataBase db;
    private DossierMedicaleDao dossierMedicaleDao;

    private EditText nameEditText, lastnameEditText, addressEditText, bloodTypeEditText, maladieEditText, phoneNumberEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dossier_medicale);

        // Initialize UI elements
        recyclerView = findViewById(R.id.recyclerView);
        nameEditText = findViewById(R.id.nameEditText);
        lastnameEditText = findViewById(R.id.lastnameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        bloodTypeEditText = findViewById(R.id.bloodTypeEditText);
        maladieEditText = findViewById(R.id.maladieEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        addButton = findViewById(R.id.addButton);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize database and DAO
        db = AppDataBase.getinstance(this);
        dossierMedicaleDao = db.dossierMedicaleDao();

        // Initialize the adapter and set it to the RecyclerView
        dossierList = new ArrayList<>();
        adapter = new DossierMedicaleAdapter(dossierList);
        recyclerView.setAdapter(adapter);

        // Load existing DossierMedicale data
        loadDossierMedicaleData();

        // Add button listener to insert new DossierMedicale
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewDossierMedicale();
            }
        });
    }

    private void loadDossierMedicaleData() {
        // Fetch the data from the database asynchronously
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<DossierMedicale> loadedData = dossierMedicaleDao.getAllDossiers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dossierList.clear();
                        dossierList.addAll(loadedData);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void addNewDossierMedicale() {
        // Get the values from the EditTexts
        String name = nameEditText.getText().toString();
        String lastname = lastnameEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String bloodType = bloodTypeEditText.getText().toString();
        String maladie = maladieEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();

        // Validate the input
        if (name.isEmpty() || lastname.isEmpty() || address.isEmpty() || bloodType.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new DossierMedicale object
        DossierMedicale newDossier = new DossierMedicale(name, lastname, address, bloodType, maladie, phoneNumber);

        // Insert the new DossierMedicale into the database
        new Thread(new Runnable() {
            @Override
            public void run() {
                dossierMedicaleDao.insert(newDossier);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadDossierMedicaleData();
                        Toast.makeText(DossierMedicaleActivity.this, "Dossier added successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}
