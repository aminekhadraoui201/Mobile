package com.example.projetmobile.Database;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.projetmobile.DAO.DossierMedicaleDao;
import com.example.projetmobile.DAO.RendezVousDao;
import com.example.projetmobile.DAO.UserDao;
import com.example.projetmobile.Entite.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
public static AppDataBase instance;

public abstract UserDao userDao();

    public static AppDataBase getinstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "user")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


    public DossierMedicaleDao DossierMedicaleDao() {
        return null;
    }

    public RendezVousDao rendezVousDao() {
        return null;
    }
}
