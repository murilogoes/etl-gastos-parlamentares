package com.fbd.gastoparlamentar.model;

import javax.persistence.*;

@Entity
@Table(name = "passagem_aerea")
public class PassagemAerea {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_passagemAerea")
    private Integer id;

    @Column(name = "passageiro")
    private String passageiro;

    @Column(name = "trecho")
    private String trecho;

    @Column(name = "id_despesa")
    private Integer idDespesa;


    public PassagemAerea() {
    }

    public PassagemAerea(String passageiro, String trecho, Integer idDespesa) {
        this.passageiro = passageiro;
        this.trecho = trecho;
        this.idDespesa = idDespesa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(String passageiro) {
        this.passageiro = passageiro;
    }

    public String getTrecho() {
        return trecho;
    }

    public void setTrecho(String trecho) {
        this.trecho = trecho;
    }

    public Integer getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Integer idDespesa) {
        this.idDespesa = idDespesa;
    }
}
