package com.example.projetmobile.Entite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RendezVousDao {

    // Insert a new RendezVous record
    @Insert
    void insert(RendezVous rendezVous);

    // Update an existing RendezVous record
    @Update
    void update(RendezVous rendezVous);

    // Delete a specific RendezVous record
    @Delete
    void delete(RendezVous rendezVous);

    // Get a specific RendezVous by ID
    @Query("SELECT * FROM rendezvous WHERE uid = :uid")
    RendezVous getRendezVousById(int uid);

    // Get all RendezVous records
    @Query("SELECT * FROM rendezvous")
    List<RendezVous> getAllRendezVous();

    // Delete all records (optional)
    @Query("DELETE FROM rendezvous")
    void deleteAll();
}
