package br.com.educatech.EducaTech.dtos.modulo;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;

public class ModuloDTOOut {
    private Long id;
    private String titulo;
    private String descricao;
    private Integer ordem;
    private CursoDTOOut curso;

    public ModuloDTOOut() {}

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

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public CursoDTOOut getCurso() {
        return curso;
    }

    public void setCurso(CursoDTOOut curso) {
        this.curso = curso;
    }
}
