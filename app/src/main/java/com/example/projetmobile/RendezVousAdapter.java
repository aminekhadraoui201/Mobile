package com.example.projetmobile;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Entite.RendezVous;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RendezVousAdapter extends RecyclerView.Adapter<RendezVousAdapter.RendezVousViewHolder> {

    private List<RendezVous> rendezVousList;

    public RendezVousAdapter(List<RendezVous> rendezVousList) {
        this.rendezVousList = rendezVousList;
    }

    @Override
    public RendezVousViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rendezvous, parent, false);
        return new RendezVousViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RendezVousViewHolder holder, int position) {
        RendezVous rendezVous = rendezVousList.get(position);
        holder.fullNameTextView.setText(rendezVous.getFullName());
        holder.cityTextView.setText(rendezVous.getCity());
        holder.hospitalNameTextView.setText(rendezVous.getHospitalName());

        // Format the LocalDateTime to a readable date string
        DateTimeFormatter formatter = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.dateTextView.setText(rendezVous.getDate().format(formatter));
        }
    }

    @Override
    public int getItemCount() {
        return rendezVousList.size();
    }

    public static class RendezVousViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameTextView, cityTextView, hospitalNameTextView, dateTextView;

        public RendezVousViewHolder(View itemView) {
            super(itemView);
            fullNameTextView = itemView.findViewById(R.id.fullNameTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            hospitalNameTextView = itemView.findViewById(R.id.hospitalNameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
