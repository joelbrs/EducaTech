package br.com.educatech.EducaTech.model;

import br.com.educatech.EducaTech.enums.StatusCurso;
import br.com.educatech.EducaTech.model.pk.ProgressoCursoPK;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_progresso_curso")
public class ProgressoCurso {

    @EmbeddedId
    private ProgressoCursoPK id = new ProgressoCursoPK();
    private Instant dataConclusao;
    private Long statusCurso;

    public ProgressoCurso() {}

    public Usuario getUsuario() {
        return id.getUsuario();
    }

    public Curso getCurso() {
        return id.getCurso();
    }

    public void setUsuario(Usuario usuario) {
        id.setUsuario(usuario);
    }

    public void setCurso(Curso curso) {
        id.setCurso(curso);
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
