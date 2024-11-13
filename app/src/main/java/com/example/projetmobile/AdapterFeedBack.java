package com.example.projetmobile;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.FeedBack;

import java.util.List;
public class AdapterFeedBack extends RecyclerView.Adapter<AdapterFeedBack.FeedBackViewHolder> {

    private Context context;
    private List<FeedBack> feedBackList;
    private AppDataBase appDatabase;

    public AdapterFeedBack(Context context, List<FeedBack> feedBackList, AppDataBase appDatabase) {
        this.context = context;
        this.feedBackList = feedBackList;
        this.appDatabase = appDatabase;
    }

    @NonNull
    @Override
    public FeedBackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feedback, parent, false);
        return new FeedBackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedBackViewHolder holder, int position) {
        FeedBack feedBack = feedBackList.get(position);
        holder.objetTextView.setText(feedBack.getObjet());
        holder.descriptionTextView.setText(feedBack.getDes());
        holder.etatTextView.setText("État: " + feedBack.getEtat());

        // Gérer l'action du bouton "Changer l'état"
        holder.changeEtatButton.setOnClickListener(v -> {
            if ("non".equals(feedBack.getEtat())) {
                feedBack.setEtat("oui");
            } else {
                feedBack.setEtat("non");
            }
            // Mise à jour dans la base de données
            appDatabase.feedBackDao().update(feedBack); // Mise à jour dans la DB
            notifyItemChanged(position); // Mettre à jour l'élément dans la liste
        });
    }

    @Override
    public int getItemCount() {
        return feedBackList.size();
    }

    public static class FeedBackViewHolder extends RecyclerView.ViewHolder {
        TextView objetTextView, descriptionTextView, etatTextView;
        Button changeEtatButton;

        public FeedBackViewHolder(View itemView) {
            super(itemView);
            objetTextView = itemView.findViewById(R.id.feedback_objet);
            descriptionTextView = itemView.findViewById(R.id.feedback_description);
            etatTextView = itemView.findViewById(R.id.feedback_etat);
            changeEtatButton = itemView.findViewById(R.id.btn_change_etat);
        }
    }
}
