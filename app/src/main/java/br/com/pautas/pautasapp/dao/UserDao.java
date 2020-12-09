package br.com.pautas.pautasapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import br.com.pautas.pautasapp.model.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user limit 1")
    User getUser();

    @Insert()
    void insert(User user);

    @Update()
    void update(User user);
}
