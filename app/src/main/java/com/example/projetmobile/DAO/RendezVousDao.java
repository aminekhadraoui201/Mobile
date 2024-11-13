package com.example.projetmobile.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetmobile.Entite.RendezVous;

import java.util.List;

@Dao
public interface RendezVousDao {

    // Insert a new rendezvous
    @Insert
    long insertRendezVous(RendezVous rendezVous);

    // Update an existing rendezvous
    @Update
    int updateRendezVous(RendezVous rendezVous);

    // Delete a specific rendezvous
    @Delete
    void deleteRendezVous(RendezVous rendezVous);

    // Get all rendezvous entries
    @Query("SELECT * FROM rendez_vous")
    List<RendezVous> getAllRendezVous();

    // Get a rendezvous by its unique ID
    @Query("SELECT * FROM rendez_vous WHERE uid = :uid LIMIT 1")
    RendezVous getRendezVousById(int uid);

    // Delete all entries in the rendez_vous table
    @Query("DELETE FROM rendez_vous")
    void deleteAll();
}
