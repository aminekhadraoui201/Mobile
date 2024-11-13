package com.example.projetmobile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile.Entite.DossierMedicale;
import com.example.projetmobile.R;

import java.util.List;

public class DossierMedicaleAdapter extends RecyclerView.Adapter<DossierMedicaleAdapter.DossierMedicaleViewHolder> {

    private List<DossierMedicale> dossierList;

    public DossierMedicaleAdapter(List<DossierMedicale> dossierList) {
        this.dossierList = dossierList;
    }

    @Override
    public DossierMedicaleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout (item_dossier_medicale.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dossier_medicale, parent, false);
        return new DossierMedicaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DossierMedicaleViewHolder holder, int position) {
        // Get the current DossierMedicale item
        DossierMedicale dossierMedicale = dossierList.get(position);

        // Set the data for each TextView
        holder.nameTextView.setText(dossierMedicale.getName());
        holder.lastnameTextView.setText(dossierMedicale.getLastname());
        holder.bloodTypeTextView.setText(dossierMedicale.getBloodType());
        holder.phoneNumberTextView.setText(dossierMedicale.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return dossierList.size();
    }

    public static class DossierMedicaleViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView lastnameTextView;
        TextView bloodTypeTextView;
        TextView phoneNumberTextView;

        public DossierMedicaleViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            lastnameTextView = itemView.findViewById(R.id.lastnameTextView);
            bloodTypeTextView = itemView.findViewById(R.id.bloodTypeTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
        }
    }
}
