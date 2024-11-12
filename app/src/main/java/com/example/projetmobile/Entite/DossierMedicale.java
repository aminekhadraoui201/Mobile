package com.example.projetmobile.Entite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dossier_medicale")
public class DossierMedicale {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String name;
    private String lastname;
    private String address;   // Corrected spelling for clarity
    private String bloodType;
    private String maladie;
    private String phoneNumber;

    // Constructor
    public DossierMedicale(String name, String lastname, String address, String bloodType, String maladie, String phoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.bloodType = bloodType;
        this.maladie = maladie;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getMaladie() {
        return maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
