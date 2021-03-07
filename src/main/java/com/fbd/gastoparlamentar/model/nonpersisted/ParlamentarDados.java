package com.fbd.gastoparlamentar.model.nonpersisted;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fbd.gastoparlamentar.model.Parlamentar;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParlamentarDados {
    private List<Parlamentar> dados;

    public ParlamentarDados() {
    }

    public List<Parlamentar> getDados() {
        return dados;
    }

    public void setDados(List<Parlamentar> dados) {
        this.dados = dados;
    }

}
