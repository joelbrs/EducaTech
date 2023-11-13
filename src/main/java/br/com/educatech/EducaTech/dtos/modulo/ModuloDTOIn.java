package br.com.educatech.EducaTech.dtos.modulo;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;

public class ModuloDTOIn {
    private String titulo;
    private String descricao;
    private Integer ordem;
    private Long curso;

    public ModuloDTOIn() {}

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

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Long getCurso() {
        return curso;
    }

    public void setCurso(Long curso) {
        this.curso = curso;
    }
}
