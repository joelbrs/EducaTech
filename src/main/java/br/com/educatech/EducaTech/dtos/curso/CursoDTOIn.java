package br.com.educatech.EducaTech.dtos.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CursoDTOIn {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal cargaHoraria;
    private String imagem;

    public CursoDTOIn() {}

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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
