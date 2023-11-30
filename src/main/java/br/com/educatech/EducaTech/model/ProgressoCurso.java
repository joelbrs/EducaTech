package br.com.educatech.EducaTech.model;

import br.com.educatech.EducaTech.enums.StatusCurso;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_progresso_curso")
public class ProgressoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private Instant dataConclusao;
    private Long statusCurso;

    public ProgressoCurso() {}

    public ProgressoCurso(Usuario usuario, Curso curso) {
        this.curso = curso;
        this.usuario = usuario;
        this.statusCurso = 1L;
        this.dataConclusao = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Instant getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Instant dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public StatusCurso getStatusCurso() throws Exception {
        return StatusCurso.valueOf(statusCurso);
    }

    public void setStatusCurso(Long statusCurso) {
        if (statusCurso != null) {
            this.statusCurso = statusCurso;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressoCurso progressoCurso = (ProgressoCurso) o;
        return Objects.equals(id, progressoCurso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
