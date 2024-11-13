package com.example.projetmobile.Entite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;

@Entity(tableName = "rendezvous")
public class RendezVous {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String fullName;
    private String city;
    private String hospitalName;
    private String date;

    // Constructor
    public RendezVous(String fullName, String city, String hospitalName, String date) {
        this.fullName = fullName;
        this.city = city;
        this.hospitalName = hospitalName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}