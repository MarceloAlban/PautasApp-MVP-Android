package br.com.pautas.pautasapp.contract;

import java.util.List;

import br.com.pautas.pautasapp.model.Pauta;

public interface IPauta {
    String STATUS_OPEN = "A";
    String STATUS_CLOSED = "F";

    void insert(Pauta pauta);

    List<Pauta> getAll(String status);

    void update(Pauta pauta);
}
