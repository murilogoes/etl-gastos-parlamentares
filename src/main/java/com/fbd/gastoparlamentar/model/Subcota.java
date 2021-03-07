package com.fbd.gastoparlamentar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subcota")
public class Subcota {

    @Id
    @Column(name = "id_subcota")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    public Subcota() {
    }

    public Subcota(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
