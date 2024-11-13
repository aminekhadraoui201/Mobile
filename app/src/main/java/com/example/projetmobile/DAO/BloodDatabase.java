package com.example.projetmobile.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projetmobile.Entite.BloodRequest;

@Database(entities = {BloodRequest.class}, version = 1)
public abstract class BloodDatabase extends RoomDatabase {

    private static volatile BloodDatabase instance;

    public abstract BloodRequestDao bloodRequestDao();

    public static BloodDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (BloodDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    BloodDatabase.class, "BloodRequests.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}