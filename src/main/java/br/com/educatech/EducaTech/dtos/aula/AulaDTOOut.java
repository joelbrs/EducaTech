package br.com.educatech.EducaTech.dtos.aula;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOOut;

public class AulaDTOOut {

    private CursoDTOOut curso;
    private ModuloDTOOut modulo;
    private String titulo;
    private String descricao;
    private Boolean assistida;
    private Integer ordem;

    public AulaDTOOut() {}

    public CursoDTOOut getCurso() {
        return curso;
    }

    public void setCurso(CursoDTOOut curso) {
        this.curso = curso;
    }

    public ModuloDTOOut getModulo() {
        return modulo;
    }

    public void setModulo(ModuloDTOOut modulo) {
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