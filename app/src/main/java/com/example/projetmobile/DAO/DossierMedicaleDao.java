package com.example.projetmobile.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetmobile.Entite.DossierMedicale;

import java.util.List;

@Dao
public interface DossierMedicaleDao {

    // Insert a new DossierMedicale record
    @Insert
    void insert(DossierMedicale dossierMedicale);

    // Update an existing DossierMedicale record
    @Update
    void update(DossierMedicale dossierMedicale);

    // Delete a specific DossierMedicale record
    @Delete
    void delete(DossierMedicale dossierMedicale);

    // Get a specific DossierMedicale record by ID
    @Query("SELECT * FROM dossier_medicale WHERE uid = :uid")
    DossierMedicale getDossierById(int uid);

    // Get all DossierMedicale records
    @Query("SELECT * FROM dossier_medicale")
    List<DossierMedicale> getAllDossiers();

    // Delete all records (optional)
    @Query("DELETE FROM dossier_medicale")
    void deleteAll();
}