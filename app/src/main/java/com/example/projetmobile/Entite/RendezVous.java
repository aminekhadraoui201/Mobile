package com.example.projetmobile.Entite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rendez_vous")
public class RendezVous {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String fullName;
    private String note;
    private String typeRendezVous;
    private String location;
    private String date;

    // Constructor
    public RendezVous( String fullName, String note, String typeRendezVous, String location, String date) {
        this.fullName = fullName;
        this.note = note;
        this.typeRendezVous = typeRendezVous;
        this.location = location;
        this.date = date;
    }

    // Getters and Setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTypeRendezVous() {
        return typeRendezVous;
    }

    public void setTypeRendezVous(String typeRendezVous) {
        this.typeRendezVous = typeRendezVous;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
