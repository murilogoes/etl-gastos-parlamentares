package com.fbd.gastoparlamentar.model;

import javax.persistence.*;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "cnpj_valido")
    private Boolean cnpjValido;


    public Fornecedor() {
    }

    public Fornecedor(Integer id, String descricao, String cnpj, Boolean cnpjValido) {
        this.id = id;
        this.descricao = descricao;
        this.cnpj = cnpj;
        this.cnpjValido = cnpjValido;
    }

    public Fornecedor(String descricao, String cnpj, Boolean cnpjValido) {
        this.descricao = descricao;
        this.cnpj = cnpj;
        this.cnpjValido = cnpjValido;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Boolean getCnpjValido() {
        return cnpjValido;
    }

    public void setCnpjValido(Boolean cnpjValido) {
        this.cnpjValido = cnpjValido;
    }
}
