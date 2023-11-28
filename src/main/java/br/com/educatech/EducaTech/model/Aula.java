package br.com.educatech.EducaTech.model;

import br.com.educatech.EducaTech.model.pk.ModuloCursoPK;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_aula")
public class Aula {

    @EmbeddedId
    private ModuloCursoPK id = new ModuloCursoPK();
    private String titulo;
    private String descricao;
    private Boolean assistida;
    private String video;

    public Aula() {}

    public Aula(Curso curso, Modulo modulo, String titulo, String descricao, Boolean assistida, Integer ordem, String video) {
        setCurso(curso);
        setModulo(modulo);
        setOrdem(ordem);
        this.titulo = titulo;
        this.descricao = descricao;
        this.assistida = assistida;
        this.video = video;
    }

    public Curso getCurso() {
        return id.getCurso();
    }

    public void setCurso(Curso curso) {
        id.setCurso(curso);
    }

    public Modulo getModulo() {
        return id.getModulo();
    }

    public void setModulo(Modulo modulo) {
        id.setModulo(modulo);
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
        return id.getOrdem();
    }

    public void setOrdem(Integer ordem) {
        this.id.setOrdem(ordem);
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(id, aula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
