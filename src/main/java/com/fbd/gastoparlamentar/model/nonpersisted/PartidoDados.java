package com.fbd.gastoparlamentar.model.nonpersisted;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fbd.gastoparlamentar.model.Partido;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartidoDados {
    private List<Partido> dados;

    public PartidoDados() {
    }

    public List<Partido> getDados() {
        return dados;
    }

    public void setDados(List<Partido> dados) {
        this.dados = dados;
    }

}
