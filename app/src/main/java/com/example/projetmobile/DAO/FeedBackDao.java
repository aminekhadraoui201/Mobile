package com.example.projetmobile.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetmobile.Entite.FeedBack;
import com.example.projetmobile.Entite.User;

import java.util.List;
@Dao
public interface FeedBackDao {
    @Insert
    void insert(FeedBack feedBack);
    @Update
    void update(FeedBack feedBack);
    @Delete
    void delete(FeedBack feedBack);
    @Query("SELECT * FROM FeedBack")
    List<FeedBack> getAllFeedBacks();
    @Query("SELECT * FROM FeedBack WHERE etat='non'")
    List<FeedBack> getAllFeedbackNonTraite();
    @Query("SELECT * FROM FeedBack WHERE etat='oui'")
    List<FeedBack> getAllFeedbackTraite();
}
