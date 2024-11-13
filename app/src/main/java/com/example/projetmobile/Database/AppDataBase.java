package com.example.projetmobile.Database;

import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projetmobile.DAO.DossierMedicaleDao;
import com.example.projetmobile.DAO.UserDao;
import com.example.projetmobile.Entite.DossierMedicale;
import com.example.projetmobile.Entite.User;

@Database(entities = {User.class, DossierMedicale.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public static AppDataBase instance;

    public abstract UserDao userDao();
    public abstract DossierMedicaleDao dossierMedicaleDao();

    // Migration example: from version 1 to version 2
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Example: Add a new column to the DossierMedicale table
            database.execSQL("ALTER TABLE DossierMedicale ADD COLUMN new_column INTEGER DEFAULT 0 NOT NULL");
        }
    };

    public static AppDataBase getinstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "user2")
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2) // Include the migration
                    .build();
        }
        return instance;
    }
}
