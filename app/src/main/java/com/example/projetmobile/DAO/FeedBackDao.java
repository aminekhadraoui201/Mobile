package com.example.projetmobile.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projetmobile.Entite.FeedBack;
import com.example.projetmobile.Entite.User;

import java.util.List;
@Dao
public interface FeedBackDao {
    @Insert
    void insertfeedback(FeedBack feedBack);
    @Delete
    void deletefeedback (FeedBack feedBack);
    @Query("SELECT * FROM FeedBack")
    List<User> getAllfeedback();
    @Query("SELECT * FROM FeedBack WHERE etat='non'")
    List<User> getAllFeedbackNonTraite();
    @Query("SELECT * FROM FeedBack WHERE etat='oui'")
    List<User> getAllFeedbackTraite();
}
