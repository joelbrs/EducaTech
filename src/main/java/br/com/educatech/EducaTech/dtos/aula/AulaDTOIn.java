package br.com.educatech.EducaTech.dtos.aula;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AulaDTOIn {

    @NotNull
    private Long curso;

    @NotNull
    private Long modulo;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;
    private Boolean assistida;

    @NotNull
    private Integer ordem;

    public AulaDTOIn() {}

    public Long getCurso() {
        return curso;
    }

    public void setCurso(Long curso) {
        this.curso = curso;
    }

    public Long getModulo() {
        return modulo;
    }

    public void setModulo(Long modulo) {
        this.modulo = modulo;
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

    public Boolean getAssistida() {
        return assistida;
    }

    public void setAssistida(Boolean assistida) {
        this.assistida = assistida;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
