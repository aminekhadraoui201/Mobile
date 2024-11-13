package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnViewRequests;
    private Button btnAddRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewRequests = findViewById(R.id.button_view_requests);
        btnAddRequest = findViewById(R.id.button_add_request);

        btnViewRequests.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewRequestsActivity.class)));
        btnAddRequest.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddOrEditRequestActivity.class)));
    }
}
