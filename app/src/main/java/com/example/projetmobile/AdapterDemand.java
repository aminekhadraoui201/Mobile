package com.example.projetmobile;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Entite.BloodRequest;

import java.util.List;

public class AdapterDemand extends RecyclerView.Adapter<AdapterDemand.ViewHolder> {

    private List<BloodRequest> requests;
    private OnItemClickListener listener;

    public AdapterDemand(List<BloodRequest> requests, OnItemClickListener listener) {
        this.requests = requests;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_demande, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BloodRequest request = requests.get(position);

        holder.textBloodCenter.setText("Center: " + request.getCenter());
        holder.textBloodGroup.setText("Blood Group: " + request.getBloodGroup());
        holder.textQuantity.setText("Quantity: " + request.getQuantity());
        holder.textDate.setText("Date: " + request.getDate());

        // Show "Urgent" text only if the request is urgent
        if (request.isUrgent()) {
            holder.textUrgent.setVisibility(View.VISIBLE);
            holder.textUrgent.setTypeface(holder.textUrgent.getTypeface(), Typeface.BOLD);
        } else {
            holder.textUrgent.setVisibility(View.GONE);
        }

        holder.buttonModify.setOnClickListener(v -> listener.onModify(request));
        holder.buttonDelete.setOnClickListener(v -> listener.onDelete(request));
    }


    @Override
    public int getItemCount() {
        return requests.size();
    }

    public interface OnItemClickListener {
        void onModify(BloodRequest request);
        void onDelete(BloodRequest request);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textBloodCenter, textBloodGroup, textQuantity, textDate, textUrgent;
        Button buttonModify, buttonDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textBloodCenter = itemView.findViewById(R.id.text_blood_center);
            textBloodGroup = itemView.findViewById(R.id.text_blood_group);
            textQuantity = itemView.findViewById(R.id.text_quantity);
            textDate = itemView.findViewById(R.id.text_date);
            textUrgent = itemView.findViewById(R.id.text_urgent);  // Ensure this line is present
            buttonModify = itemView.findViewById(R.id.button_modify_request);
            buttonDelete = itemView.findViewById(R.id.button_delete_request);
        }
    }

}
