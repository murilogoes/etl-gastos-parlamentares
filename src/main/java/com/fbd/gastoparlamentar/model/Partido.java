package com.fbd.gastoparlamentar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partido")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Partido {

    @Id
    @Column(name = "id_partido")
    private Integer id;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "nome")
    private String nome;

    public Partido() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
