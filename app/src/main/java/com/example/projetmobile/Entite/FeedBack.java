package com.example.projetmobile.Entite;

import androidx.room.Entity;
import androidx.room.PrimaryKey;




@Entity(tableName = "FeedBack")
public class FeedBack {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String objet;
    private String des;
    private String etat="non";

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public FeedBack() {
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public FeedBack(String objet, String des) {
        this.objet = objet;
        this.des = des;
    }
}


