package br.com.pautas.pautasapp.contract;

import br.com.pautas.pautasapp.model.User;

public interface IUser {
    void insert(User user);

    User getUser();

    void update(User user);
}
