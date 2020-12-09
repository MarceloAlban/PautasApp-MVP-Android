package br.com.pautas.pautasapp.model;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import br.com.pautas.pautasapp.application.ControlApplication;
import br.com.pautas.pautasapp.contract.IPauta;

@Entity
public class Pauta implements IPauta {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo()
    private String title;

    @ColumnInfo()
    private String description;

    @ColumnInfo()
    private String details;

    @ColumnInfo()
    private String author;

    @ColumnInfo()
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Ignore
    public Pauta() {
    }

    public Pauta(String title, String description, String details, String author, String status) {
        this.title = title;
        this.description = description;
        this.details = details;
        this.author = author;
        this.status = status;
    }

    @Override
    public void insert(Pauta pauta) {
        ControlApplication.mDb.pautaDao().insert(pauta);
    }

    @Override
    public List<Pauta> getAll(String status) {
        return ControlApplication.mDb.pautaDao().getAll(status);
    }

    @Override
    public void update(Pauta pauta) {
        ControlApplication.mDb.pautaDao().update(pauta);
    }
}
