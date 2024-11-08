package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.User;

import java.util.List;



import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.Database.AppDataBase;
import com.example.projetmobile.Entite.User;

import java.util.List;

public class AdapterHopital extends RecyclerView.Adapter<AdapterHopital.MyViewHolder> {
    private Context context;
    private List<User> users;
    private AppDataBase appDataBase;

    public AdapterHopital(Context context, List<User> users, AppDataBase appDataBase) {
        this.context = context;
        this.users = users;
        this.appDataBase = appDataBase;
    }
    public void updateUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,@SuppressLint("RecyclerView") int position) {
        User user = users.get(position);
        holder.nom.setText(String.valueOf(user.getNom()));
        holder.prenom.setText(String.valueOf(user.getPrenom()));
    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nom,prenom;
        Button supp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom=itemView.findViewById(R.id.nom);
            prenom=itemView.findViewById(R.id.prenom);
            supp=itemView.findViewById(R.id.supp);

        }
    }
}
