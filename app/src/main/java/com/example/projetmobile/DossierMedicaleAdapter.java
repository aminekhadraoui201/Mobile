package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Entite.DossierMedicale;

import java.util.List;

public class DossierMedicaleAdapter extends RecyclerView.Adapter<DossierMedicaleAdapter.DossierMedicaleViewHolder> {

    private List<DossierMedicale> dossierList;

    public DossierMedicaleAdapter(List<DossierMedicale> dossierList) {
        this.dossierList = dossierList;
    }

    @Override
    public DossierMedicaleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the correct layout for each item (not the activity layout)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dossier_medicale, parent, false);
        return new DossierMedicaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DossierMedicaleViewHolder holder, int position) {
        DossierMedicale dossier = dossierList.get(position);

        // Set the data for each TextView
        holder.nameTextView.setText(dossier.getName());
        holder.lastnameTextView.setText(dossier.getLastname());
        holder.addTextView.setText(dossier.getAddress());
        holder.bloodTypeTextView.setText(dossier.getBloodType());
        holder.phoneNumberTextView.setText(dossier.getPhoneNumber());
        // Set the correct address
        holder.maladieTextView.setText(dossier.getMaladie()); // Set the correct maladie
    }

    @Override
    public int getItemCount() {
        return dossierList.size();
    }

    // ViewHolder class for each item
    public static class DossierMedicaleViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, lastnameTextView, bloodTypeTextView, phoneNumberTextView, addTextView, maladieTextView;

        @SuppressLint("WrongViewCast")
        public DossierMedicaleViewHolder(View itemView) {
            super(itemView);
            // Initialize the views
            nameTextView = itemView.findViewById(R.id.nameTextView);
            lastnameTextView = itemView.findViewById(R.id.lastnameTextView);
            addTextView = itemView.findViewById(R.id.addTextView);
            bloodTypeTextView = itemView.findViewById(R.id.bloodTypeTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
          // Correct ID for address
            maladieTextView = itemView.findViewById(R.id.maladieTextView); // Correct ID for maladie
        }
    }
}
