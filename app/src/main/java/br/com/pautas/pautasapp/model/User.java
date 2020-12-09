package br.com.pautas.pautasapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import br.com.pautas.pautasapp.application.ControlApplication;
import br.com.pautas.pautasapp.contract.IUser;

@Entity
public class User implements IUser {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo()
    private String name;

    @ColumnInfo()
    private String email;

    @ColumnInfo()
    private String password;

    @ColumnInfo()
    private boolean logged;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    @Ignore
    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public void insert(User user) {
        ControlApplication.mDb.userDao().insert(user);
    }

    @Override
    public void update(User user) {
        ControlApplication.mDb.userDao().update(user);
    }

    @Override
    public User getUser() {
        return ControlApplication.mDb.userDao().getUser();
    }
}
