package br.com.educatech.EducaTech.model;

import br.com.educatech.EducaTech.enums.StatusCurso;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;


/**
 * Entidade ProgressoCurso, referenciada pela tabela "tb_progresso_curso"
 * */
@Entity
@Table(name = "tb_progresso_curso")
public class ProgressoCurso {

    /**
     * Atributos da entidade em questão, apresentando, inclusive, seus relacionamentos
     * com outras entidades do sistema
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Relacionamento "muitos-para-um" com a entidade Usuário, criando um atributo "usuario_id" na tabela,
     * referenciando esse relacionamento
     * */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /**
     * Relacionamento "muitos-para-um" com a entidade Curso, criando um atributo "curso_id" na tabela,
     * referenciando esse relacionamento
     * */
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private Instant dataConclusao;
    private Long statusCurso;

    /**
     * Construtores, Getters & Setters e HashCode & Equals da Entidade e de seus atributos
     * */

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
