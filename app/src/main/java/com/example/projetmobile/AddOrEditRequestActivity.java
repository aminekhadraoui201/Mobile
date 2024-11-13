package com.example.projetmobile;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetmobile.database.BloodDatabase;
import com.example.projetmobile.Entite.BloodRequest;

import java.util.Calendar;
import java.util.concurrent.Executors;

public class AddOrEditRequestActivity extends AppCompatActivity {

    private Spinner spinnerBloodCenter, spinnerBloodGroup;
    private CheckBox checkboxUrgency;
    private EditText inputContactInfo, inputQuantity, inputDate;
    private Button btnSubmit;
    private BloodDatabase db;
    private BloodRequest requestToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_request);

        db = BloodDatabase.getInstance(this);

        // Initialize UI components
        spinnerBloodCenter = findViewById(R.id.spinner_blood_center);
        spinnerBloodGroup = findViewById(R.id.spinner_blood_group);
        checkboxUrgency = findViewById(R.id.checkbox_urgency);
        inputContactInfo = findViewById(R.id.input_contact_info);
        inputQuantity = findViewById(R.id.input_quantity);
        inputDate = findViewById(R.id.input_date);
        btnSubmit = findViewById(R.id.btn_submit_request);

        // Check if we are in "edit" mode
        if (getIntent().hasExtra("request")) {
            requestToEdit = (BloodRequest) getIntent().getSerializableExtra("request");
            populateFields(requestToEdit);
            btnSubmit.setText("Update Request");
        } else {
            btnSubmit.setText("Add Request");
        }

        inputDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(AddOrEditRequestActivity.this, (view, selectedYear, selectedMonth, selectedDay) -> {
                String date = (selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear;
                inputDate.setText(date);
            }, year, month, day);
            datePickerDialog.show();
        });

        btnSubmit.setOnClickListener(v -> {
            if (requestToEdit == null) {
                addRequest();
            } else {
                updateRequest();
            }
        });
    }

    private void populateFields(BloodRequest request) {
        inputContactInfo.setText(request.getContactInfo());
        inputQuantity.setText(String.valueOf(request.getQuantity()));
        inputDate.setText(request.getDate());
        checkboxUrgency.setChecked(request.isUrgent());
        // Set spinners to match selected blood center and blood group if needed
    }

    private void addRequest() {
        if (validateFields()) {
            BloodRequest request = new BloodRequest(
                    spinnerBloodCenter.getSelectedItem().toString(),
                    spinnerBloodGroup.getSelectedItem().toString(),
                    Integer.parseInt(inputQuantity.getText().toString()),
                    checkboxUrgency.isChecked(),
                    inputContactInfo.getText().toString(),
                    inputDate.getText().toString()
            );

            Executors.newSingleThreadExecutor().execute(() -> {
                db.bloodRequestDao().insertRequest(request);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Request Added", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        }
    }

    private void updateRequest() {
        if (validateFields()) {
            requestToEdit.setCenter(spinnerBloodCenter.getSelectedItem().toString());
            requestToEdit.setBloodGroup(spinnerBloodGroup.getSelectedItem().toString());
            requestToEdit.setQuantity(Integer.parseInt(inputQuantity.getText().toString()));
            requestToEdit.setUrgency(checkboxUrgency.isChecked());
            requestToEdit.setContactInfo(inputContactInfo.getText().toString());
            requestToEdit.setDate(inputDate.getText().toString());

            Executors.newSingleThreadExecutor().execute(() -> {
                db.bloodRequestDao().updateRequest(requestToEdit);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Request Updated", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        }
    }

    private boolean validateFields() {
        if (inputContactInfo.getText().toString().isEmpty() ||
                inputQuantity.getText().toString().isEmpty() ||
                inputDate.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
