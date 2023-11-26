package br.com.educatech.EducaTech.model;

import br.com.educatech.EducaTech.enums.StatusCurso;
import br.com.educatech.EducaTech.model.pk.ProgressoPK;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_progresso")
public class Progresso {

    @EmbeddedId
    private ProgressoPK id = new ProgressoPK();
    private BigDecimal porcentagem;
    private Instant dataConclusao;
    private BigDecimal pontuacaoFinal;
    private Long statusCurso;

    public Progresso() {}

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

    public BigDecimal getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(BigDecimal porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Instant getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Instant dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public BigDecimal getPontuacaoFinal() {
        return pontuacaoFinal;
    }

    public void setPontuacaoFinal(BigDecimal pontuacaoFinal) {
        this.pontuacaoFinal = pontuacaoFinal;
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
        Progresso progresso = (Progresso) o;
        return Objects.equals(id, progresso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
