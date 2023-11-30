package br.com.educatech.EducaTech.dtos.curso;

import br.com.educatech.EducaTech.dtos.curso.certificado.ModeloCertificadoDTO;

import java.math.BigDecimal;

public class CursoDTOSemArquivo {

    private Long id;
    private String titulo;
    private String descricao;
    private BigDecimal cargaHoraria;
    private Integer qtdAulas = 0;
    private Integer qtdModulos = 0;

    public CursoDTOSemArquivo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(BigDecimal cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Integer getQtdAulas() {
        return qtdAulas;
    }

    public void setQtdAulas(Integer qtdAulas) {
        this.qtdAulas = qtdAulas;
    }

    public Integer getQtdModulos() {
        return qtdModulos;
    }

    public void setQtdModulos(Integer qtdModulos) {
        this.qtdModulos = qtdModulos;
    }
}
