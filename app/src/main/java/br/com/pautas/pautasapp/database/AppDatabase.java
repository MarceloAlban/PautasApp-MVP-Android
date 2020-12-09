package br.com.pautas.pautasapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import br.com.pautas.pautasapp.model.Pauta;
import br.com.pautas.pautasapp.model.User;
import br.com.pautas.pautasapp.dao.PautaDao;
import br.com.pautas.pautasapp.dao.UserDao;

@Database(entities = {User.class, Pauta.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract PautaDao pautaDao();
}
