package com.fbd.gastoparlamentar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "parlamentar")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parlamentar {
    @Id
    @Column(name = "id_parlamentar")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Transient
    private String siglaPartido;

    @Transient
    private String siglaUf;

    @Column(name = "lideranca")
    private Boolean lideranca;

    @Column(name = "id_legislatura")
    private Integer idLegislatura;

    @Column(name = "id_partido")
    private Integer idPartido;

    @Column(name = "id_uf")
    private Integer idUf;

    public Parlamentar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLideranca() {
        return lideranca;
    }

    public void setLideranca(Boolean lideranca) {
        this.lideranca = lideranca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public Integer getIdLegislatura() {
        return idLegislatura;
    }

    public void setIdLegislatura(Integer idLegislatura) {
        this.idLegislatura = idLegislatura;
    }

    public Integer getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public Integer getIdUf() {
        return idUf;
    }

    public void setIdUf(Integer idUf) {
        this.idUf = idUf;
    }
}
