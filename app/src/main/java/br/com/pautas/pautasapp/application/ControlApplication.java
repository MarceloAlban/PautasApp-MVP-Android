package br.com.pautas.pautasapp.application;

import android.app.Application;

import androidx.room.Room;
import br.com.pautas.pautasapp.database.AppDatabase;

public class ControlApplication extends Application {
    public static AppDatabase mDb;

    @Override
    public void onCreate() {
        super.onCreate();

        mDb = Room.databaseBuilder(
                this,
                AppDatabase.class,
                "dbPautas")
                .allowMainThreadQueries()
                .build();
    }
}
