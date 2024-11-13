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
import com.example.projetmobile.Entite.DossierMedicale;
import com.example.projetmobile.Entite.RendezVous;
import com.example.projetmobile.Entite.User;

@Database(entities = {User.class, DossierMedicale.class, RendezVous.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
public static AppDataBase instance;

public abstract UserDao userDao();
public abstract DossierMedicaleDao dossierMedicaleDao ();
public abstract RendezVousDao rendezVousDao();
    public static AppDataBase getinstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "user")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
