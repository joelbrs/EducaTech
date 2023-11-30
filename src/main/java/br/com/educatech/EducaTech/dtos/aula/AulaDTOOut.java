package br.com.educatech.EducaTech.dtos.aula;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.dtos.curso.CursoDTOSemArquivo;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOOut;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOSemArquivo;

public class AulaDTOOut {

    private Long id;
    private CursoDTOSemArquivo curso;
    private ModuloDTOSemArquivo modulo;
    private String titulo;
    private String descricao;
    private Boolean assistida;
    private Integer ordem;
    private String video;

    public AulaDTOOut() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CursoDTOSemArquivo getCurso() {
        return curso;
    }

    public void setCurso(CursoDTOSemArquivo curso) {
        this.curso = curso;
    }

    public ModuloDTOSemArquivo getModulo() {
        return modulo;
    }

    public void setModulo(ModuloDTOSemArquivo modulo) {
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
