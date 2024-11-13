package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.adapter.DemandAdapter;
import com.example.projetmobile.database.BloodDatabase;
import com.example.projetmobile.Entite.BloodRequest;

import java.util.List;
import java.util.concurrent.Executors;


public class ViewRequestsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DemandAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);

        // Use androidx.appcompat.widget.Toolbar here
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Enable the back arrow in the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Handle the back arrow click to go back to the previous screen
        toolbar.setNavigationOnClickListener(v -> finish());

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recycler_view_demands);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load the list of requests from the database
        loadRequests();
    }



    private void loadRequests() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                List<BloodRequest> requests = BloodDatabase.getInstance(this).bloodRequestDao().getAllRequests();
                runOnUiThread(() -> {
                    if (requests.isEmpty()) {
                        Toast.makeText(this, "No requests available", Toast.LENGTH_SHORT).show();
                    } else {
                        adapter = new DemandAdapter(requests, new DemandAdapter.OnItemClickListener() {
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
                    }
                });
            } catch (Exception e) {
                Log.e("ViewRequestsActivity", "Error loading requests from database", e);
                runOnUiThread(() -> Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void openEditRequestActivity(BloodRequest request) {
        Intent intent = new Intent(this, AddOrEditRequestActivity.class);
        intent.putExtra("request", request); // Passing the request object to the activity
        startActivity(intent);
    }

    private void deleteRequest(BloodRequest request) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                BloodDatabase.getInstance(this).bloodRequestDao().deleteRequest(request);
                runOnUiThread(() -> {
                    Toast.makeText(ViewRequestsActivity.this, "Request Deleted", Toast.LENGTH_SHORT).show();
                    loadRequests(); // Refresh the list after deletion
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
}
