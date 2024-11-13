package com.example.projetmobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.FeedBack;

public class FeedBackAdd extends AppCompatActivity {

    private EditText objt, des;
    private Button clearButton, valideButton;

    private AppDataBase db;
    private FeedBack feedBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed_back);

        // Récupération des vues
        objt = findViewById(R.id.objt);
        des = findViewById(R.id.des);
        clearButton = findViewById(R.id.clear);
        valideButton = findViewById(R.id.valide);

        // Initialisation de la base de données Room
        db = AppDataBase.getinstance(this);
        // Action du bouton "Clear" pour vider les champs
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objt.setText("");
                des.setText("");
            }
        });
        valideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objetText = objt.getText().toString();
                String desText = des.getText().toString();

                // Vérifier que les champs ne sont pas vides
                if (objetText.isEmpty() || desText.isEmpty()) {
                    Toast.makeText(FeedBackAdd.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Créer un nouveau feedback avec les données saisies
                    FeedBack newFeedBack = new FeedBack(objetText, desText);
                    db.feedBackDao().insert(newFeedBack); // Insertion dans la base de données
                    Toast.makeText(FeedBackAdd.this, "Feedback ajouté avec succès", Toast.LENGTH_SHORT).show();

                    // Réinitialiser les champs
                    objt.setText("");
                    des.setText("");
                }
            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}