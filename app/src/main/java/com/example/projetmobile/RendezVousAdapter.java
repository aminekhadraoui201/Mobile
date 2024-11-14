package com.example.projetmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Entite.RendezVous;

import java.util.List;

public class RendezVousAdapter extends RecyclerView.Adapter<RendezVousAdapter.ViewHolder> {

    private List<RendezVous> rendezVousList;

    public RendezVousAdapter(List<RendezVous> rendezVousList) {
        this.rendezVousList = rendezVousList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rendez_vous, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RendezVous rendezVous = rendezVousList.get(position);
        holder.fullNameTextView.setText(rendezVous.getFullName());
        holder.noteTextView.setText(rendezVous.getNote());
        holder.typeRendezVousTextView.setText(rendezVous.getTypeRendezVous());
        holder.locationTextView.setText(rendezVous.getLocation());
        holder.dateTextView.setText(rendezVous.getDate());
    }

    @Override
    public int getItemCount() {
        return rendezVousList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullNameTextView, noteTextView, typeRendezVousTextView, locationTextView, dateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            fullNameTextView = itemView.findViewById(R.id.fullNameTextView);
            noteTextView = itemView.findViewById(R.id.noteTextView);
            typeRendezVousTextView = itemView.findViewById(R.id.typeRendezVousTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}

