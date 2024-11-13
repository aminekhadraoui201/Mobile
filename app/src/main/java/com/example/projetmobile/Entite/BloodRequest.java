package com.example.projetmobile.Entite;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "blood_requests")
public class BloodRequest implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String center;
    private String bloodGroup;
    private int quantity;
    private boolean urgency;
    private String contactInfo;
    private String date;

    public BloodRequest(String center, String bloodGroup, int quantity, boolean urgency, String contactInfo, String date) {
        this.center = center;
        this.bloodGroup = bloodGroup;
        this.quantity = quantity;
        this.urgency = urgency;
        this.contactInfo = contactInfo;
        this.date = date;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCenter() {
        return center;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isUrgent() {
        return urgency;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getDate() {
        return date;
    }

    public boolean getUrgency() {
        return urgency;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUrgency(boolean urgency) {
        this.urgency = urgency;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
