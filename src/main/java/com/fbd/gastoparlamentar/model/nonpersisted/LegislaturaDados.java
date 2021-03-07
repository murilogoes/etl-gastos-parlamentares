package com.fbd.gastoparlamentar.model.nonpersisted;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fbd.gastoparlamentar.model.Legislatura;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LegislaturaDados {
    private List<Legislatura> dados;

    public LegislaturaDados() {
    }

    public List<Legislatura> getDados() {
        return dados;
    }

    public void setDados(List<Legislatura> dados) {
        this.dados = dados;
    }


}
