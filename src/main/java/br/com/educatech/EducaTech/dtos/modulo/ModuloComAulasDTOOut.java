package br.com.educatech.EducaTech.dtos.modulo;

import br.com.educatech.EducaTech.dtos.aula.AulaSemModuloDTOOut;
import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.dtos.material.MaterialDTOOut;

import java.util.ArrayList;
import java.util.List;

public class ModuloComAulasDTOOut {
    private Long id;
    private String titulo;
    private String descricao;
    private Integer ordem;
    private CursoDTOOut curso;
    private MaterialDTOOut material;

    private List<AulaSemModuloDTOOut> aulas = new ArrayList<>();

    public ModuloComAulasDTOOut() {}

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

    public MaterialDTOOut getMaterial() {
        return material;
    }

    public void setMaterial(MaterialDTOOut material) {
        this.material = material;
    }

    public List<AulaSemModuloDTOOut> getAulas() {
        return aulas;
    }

    public void setAulas(List<AulaSemModuloDTOOut> aulas) {
        this.aulas = aulas;
    }
}
