package br.com.educatech.EducaTech.dtos.aula;

import br.com.educatech.EducaTech.dtos.curso.CursoDTOOut;
import br.com.educatech.EducaTech.dtos.modulo.ModuloDTOOut;

public class AulaSemModuloDTOOut {

    private Long id;
    private String titulo;
    private String descricao;
    private Integer ordem;
    private String video;
    private Long idCurso;
    private Long idModulo;
    private Boolean assistida;

    public AulaSemModuloDTOOut() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AulaSemModuloDTOOut(AulaDTOOut dto, boolean assistida) {
        this.id = dto.getId();
        this.titulo = dto.getTitulo();
        this.descricao = dto.getDescricao();
        this.ordem = dto.getOrdem();
        this.video = dto.getVideo();
        this.idCurso = dto.getCurso().getId();
        this.idModulo = dto.getModulo().getId();
        this.assistida = assistida;
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

    public Boolean getAssistida() {
        return assistida;
    }

    public void setAssistida(Boolean assistida) {
        this.assistida = assistida;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }
}
