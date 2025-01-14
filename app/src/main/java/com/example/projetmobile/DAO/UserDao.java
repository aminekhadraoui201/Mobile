package com.example.projetmobile.DAO;
import com.example.projetmobile.Entite.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDao {
    @Insert
    void insertOne(User user);
    @Update
    int updateUser(User user);
    @Delete
    void deleteUser (User user);
    @Query("SELECT * FROM user_table")
    List<User> getAll();
    @Query("SELECT * FROM user_table WHERE role='Donneur'")
    List<User> getAllDonneur();
    @Query("SELECT * FROM user_table WHERE role='Hopital'")
    List<User> getAllHopital();
}
