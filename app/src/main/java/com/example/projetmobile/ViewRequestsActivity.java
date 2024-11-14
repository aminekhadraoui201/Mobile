package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.BloodRequest;

import java.util.List;
import java.util.concurrent.Executors;

public class ViewRequestsActivity extends AppCompatActivity {
    private EditText nom, email, pass, lieu, editTextDate;

    private RecyclerView recyclerView;
    private AdapterDemand adapter;
    private NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);
        // Toolbar

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.recycler_view_demands);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notificationHelper = new NotificationHelper(this);

        loadRequests();

        Button retrn = findViewById(R.id.retrn);


        // OnClickListener pour le bouton Liste Des Rendez-vous
        retrn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacer par l'intention vers l'activité correspondante
                Intent intent = new Intent(ViewRequestsActivity.this, Admin.class);
                startActivity(intent);
            }
        });


        Button add = findViewById(R.id.retrnH);


        // OnClickListener pour le bouton Liste Des Rendez-vous
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remplacer par l'intention vers l'activité correspondante
                Intent intent = new Intent(ViewRequestsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadRequests() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                List<BloodRequest> requests = AppDataBase.getinstance(this).bloodRequestDao().getAllRequests();
                runOnUiThread(() -> {
                    if (requests.isEmpty()) {
                        Toast.makeText(this, "No requests available", Toast.LENGTH_SHORT).show();
                    } else {
                        adapter = new AdapterDemand(requests, new AdapterDemand.OnItemClickListener() {
                            @Override
                            public void onModify(BloodRequest request) {
                                openEditRequestActivity(request);
                            }

                            @Override
                            public void onDelete(BloodRequest request) {
                                deleteRequest(request);
                            }
                        });
                        recyclerView.setAdapter(adapter);

                        sendUrgentNotification(requests);  // Check for urgent requests after loading
                    }
                });
            } catch (Exception e) {
                Log.e("ViewRequestsActivity", "Error loading requests from database", e);
                runOnUiThread(() -> Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show());
            }
        });
    }
    private void sendUrgentNotification(List<BloodRequest> requests) {
        for (BloodRequest request : requests) {
            if (request.isUrgent()) {
                String title = "Urgent Blood Request";
                String message = "Urgent blood needed at " + request.getCenter() + " for blood group " + request.getBloodGroup();
                notificationHelper.sendUrgentRequestNotification(title, message);
                break;  // Notify for the first urgent request only to avoid multiple notifications
            }
        }
    }

    private void openEditRequestActivity(BloodRequest request) {
        Intent intent = new Intent(this, AddOrEditRequestActivity.class);
        intent.putExtra("request", request);  // Passing the request object to the activity
        startActivity(intent);
    }

    private void deleteRequest(BloodRequest request) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                AppDataBase.getinstance(this).bloodRequestDao().deleteRequest(request);
                runOnUiThread(() -> {
                    Toast.makeText(ViewRequestsActivity.this, "Request Deleted", Toast.LENGTH_SHORT).show();
                    loadRequests();  // Refresh the list after deletion
                });
            } catch (Exception e) {
                Log.e("ViewRequestsActivity", "Error deleting request", e);
                runOnUiThread(() -> Toast.makeText(this, "Error deleting request", Toast.LENGTH_SHORT).show());
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadRequests(); // Refresh the list when returning to this activity
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu ){

        MenuInflater menuInflater= new MenuInflater(this);
        menuInflater.inflate(R.menu.menhopital,menu);

        return true;

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profil) {
            // Lors de la navigation vers une autre activité (par exemple, depuis HopitalActivity)
            Intent intent = new Intent(ViewRequestsActivity.this, HopitalActivity.class);

            startActivity(intent);
            finish();  // Ferme l'activité actuelle
            return true;
        } else if (item.getItemId() == R.id.deco) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ViewRequestsActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }else if (item.getItemId() == R.id.ajouDon) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ViewRequestsActivity.this, AddOrEditRequestActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }else if (item.getItemId() == R.id.ListeDon) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ViewRequestsActivity.this, ViewRequestsActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }else if (item.getItemId() == R.id.deco) {
            // Handle "Deconnexion" (Logout) menu item click
            Intent logoutIntent = new Intent(ViewRequestsActivity.this, LoginActivity.class);
            startActivity(logoutIntent);
            finish(); // Close the current ProfileActivity
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
