package com.example.projetmobile.Entite;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String nom;
    private String prenom;
    private String email;
    private String mot_pass;
    private String datnaiss;
    private String role;
    private String lieu;

    public int getUid() {
        return uid;
    }

    public User() {
    }

    public User(int uid, String nom, String prenom, String email, String mot_pass, String datnaiss, String role, String lieu) {
        this.uid = uid;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_pass = mot_pass;
        this.datnaiss = datnaiss;
        this.role = role;
        this.lieu = lieu;
    }

    public User(String nom, String prenom, String email, String mot_pass, String datnaiss, String role, String lieu) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_pass = mot_pass;
        this.datnaiss = datnaiss;
        this.role = role;
        this.lieu = lieu;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_pass() {
        return mot_pass;
    }

    public void setMot_pass(String mot_pass) {
        this.mot_pass = mot_pass;
    }

    public String getDatnaiss() {
        return datnaiss;
    }

    public void setDatnaiss(String datnaiss) {
        this.datnaiss = datnaiss;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }








}
