package com.fbd.gastoparlamentar.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_despesa")
    private Integer id;

    @Column(name = "data_emissao")
    private LocalDateTime dataEmissao;

    @Column(name = "valor_documento")
    private Double valorDocumento;

    @Column(name = "valor_glosa")
    private Double valorGlosa;

    @Column(name = "valor_liquido")
    private Double valorLiquido;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "num_lote")
    private String numLote;

    @Column(name = "ide_documento")
    private String ideDocumento;

    @Column(name = "url_documento")
    private String urlDocumento;

    @Column(name = "id_subcota")
    private Integer idSubcota;

    @Column(name = "id_fornecedor")
    private Integer idFornecedor;

    @Column(name = "id_parlamentar")
    private Integer idParlamentar;

    public Despesa() {
    }

    public Despesa(LocalDateTime dataEmissao, Double valorDocumento, Double valorGlosa, Double valorLiquido, Integer mes, Integer ano, String numLote, String ideDocumento, String urlDocumento, Integer idSubcota, Integer idFornecedor, Integer idParlamentar) {
        this.dataEmissao = dataEmissao;
        this.valorDocumento = valorDocumento;
        this.valorGlosa = valorGlosa;
        this.valorLiquido = valorLiquido;
        this.mes = mes;
        this.ano = ano;
        this.numLote = numLote;
        this.ideDocumento = ideDocumento;
        this.urlDocumento = urlDocumento;
        this.idSubcota = idSubcota;
        this.idFornecedor = idFornecedor;
        this.idParlamentar = idParlamentar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Double getValorDocumento() {
        return valorDocumento;
    }

    public void setValorDocumento(Double valorDocumento) {
        this.valorDocumento = valorDocumento;
    }

    public Double getValorGlosa() {
        return valorGlosa;
    }

    public void setValorGlosa(Double valorGlosa) {
        this.valorGlosa = valorGlosa;
    }

    public Double getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(Double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getNumLote() {
        return numLote;
    }

    public void setNumLote(String numLote) {
        this.numLote = numLote;
    }

    public String getIdeDocumento() {
        return ideDocumento;
    }

    public void setIdeDocumento(String ideDocumento) {
        this.ideDocumento = ideDocumento;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public Integer getIdSubcota() {
        return idSubcota;
    }

    public void setIdSubcota(Integer idSubcota) {
        this.idSubcota = idSubcota;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Integer getIdParlamentar() {
        return idParlamentar;
    }

    public void setIdParlamentar(Integer idParlamentar) {
        this.idParlamentar = idParlamentar;
    }
}
