package br.com.pautas.pautasapp.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import br.com.pautas.pautasapp.model.Pauta;

@Dao
public interface PautaDao {
    @Query("SELECT * FROM pauta WHERE status = :status")
    List<Pauta> getAll(String status);

    @Insert
    void insert(Pauta pauta);

    @Update
    void update(Pauta pauta);
}
