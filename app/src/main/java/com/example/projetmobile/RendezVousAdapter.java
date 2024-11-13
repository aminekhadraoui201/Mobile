package com.example.projetmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile.Entite.RendezVous;
import java.util.List;

public class RendezVousAdapter extends RecyclerView.Adapter<RendezVousAdapter.RendezVousViewHolder> {

    private List<RendezVous> rendezVousList;

    public RendezVousAdapter(List<RendezVous> rendezVousList) {
        this.rendezVousList = rendezVousList;
    }

    @Override
    public RendezVousViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout (item_rendezvous.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rendezvous, parent, false);
        return new RendezVousViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RendezVousViewHolder holder, int position) {
        // Get the current RendezVous item
        RendezVous rendezVous = rendezVousList.get(position);

        // Set the data for each TextView
        holder.fullNameTextView.setText(rendezVous.getFullName());
        holder.cityTextView.setText(rendezVous.getCity());
        holder.hospitalNameTextView.setText(rendezVous.getHospitalName());
        holder.dateTextView.setText(rendezVous.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return rendezVousList.size();
    }

    public static class RendezVousViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameTextView;
        TextView cityTextView;
        TextView hospitalNameTextView;
        TextView dateTextView;

        public RendezVousViewHolder(View itemView) {
            super(itemView);
            fullNameTextView = itemView.findViewById(R.id.fullNameTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            hospitalNameTextView = itemView.findViewById(R.id.hospitalNameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
