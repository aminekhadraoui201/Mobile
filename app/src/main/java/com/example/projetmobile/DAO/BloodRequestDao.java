package com.example.projetmobile.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetmobile.Entite.BloodRequest;

import java.util.List;

@Dao
public interface BloodRequestDao {

    @Insert
    long insertRequest(BloodRequest request);

    @Update
    void updateRequest(BloodRequest request);

    @Delete
    void deleteRequest(BloodRequest request);

    @Query("SELECT * FROM blood_requests")
    List<BloodRequest> getAllRequests();
}