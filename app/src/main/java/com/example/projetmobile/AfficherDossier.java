package com.example.projetmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmobile.DAO.DossierMedicaleDao;
import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.DossierMedicale;

import java.util.List;

public class AfficherDossier extends AppCompatActivity {

    private TableLayout tableLayoutDossier;
    private AppDataBase db;
    private DossierMedicaleDao dossierMedicaleDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_dossier);

        tableLayoutDossier = findViewById(R.id.tableLayoutDossier);

        // Initialize the database and DAO
        db = AppDataBase.getinstance(this);
        dossierMedicaleDao = db.dossierMedicaleDao();

        // Load DossierMedicale data from the database
        loadDossierData();
    }

    private void loadDossierData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Fetch all DossierMedicale records
                List<DossierMedicale> loadedData = dossierMedicaleDao.getAllDossiers();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loadedData != null && !loadedData.isEmpty()) {
                            for (DossierMedicale dossier : loadedData) {
                                addRowToTable(dossier);
                            }
                        } else {
                            Toast.makeText(AfficherDossier.this, "No dossiers found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void addRowToTable(DossierMedicale dossier) {
        // Create a new row
        TableRow tableRow = (TableRow) LayoutInflater.from(this).inflate(R.layout.item_dossier_medicale_row, null);

        // Set the data for each column
        TextView nameTextView = tableRow.findViewById(R.id.nameTextView);
        TextView lastnameTextView = tableRow.findViewById(R.id.lastnameTextView);
        TextView addressTextView = tableRow.findViewById(R.id.addressTextView);
        TextView bloodTypeTextView = tableRow.findViewById(R.id.bloodTypeTextView);
        TextView maladieTextView = tableRow.findViewById(R.id.maladieTextView);
        TextView phoneNumberTextView = tableRow.findViewById(R.id.phoneNumberTextView);

        // Ensure all fields are set correctly and handle null values
        nameTextView.setText(dossier.getName() != null ? dossier.getName() : "N/A");
        lastnameTextView.setText(dossier.getLastname() != null ? dossier.getLastname() : "N/A");
        addressTextView.setText(dossier.getAddress() != null ? dossier.getAddress() : "N/A");
        bloodTypeTextView.setText(dossier.getBloodType() != null ? dossier.getBloodType() : "N/A");
        maladieTextView.setText(dossier.getMaladie() != null ? dossier.getMaladie() : "N/A");
        phoneNumberTextView.setText(dossier.getPhoneNumber() != null ? dossier.getPhoneNumber() : "N/A");

        // Add the row to the TableLayout
        tableLayoutDossier.addView(tableRow);
    }
}
