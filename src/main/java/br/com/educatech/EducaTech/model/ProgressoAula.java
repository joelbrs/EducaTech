package br.com.educatech.EducaTech.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entidade ProgressoAula, referenciada pela tabela "tb_progresso_aula"
 * */
@Entity
@Table(name = "tb_progresso_aula")
public class ProgressoAula {

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
     * Relacionamento "muitos-para-um" com a entidade Aula, criando um atributo "aula_id" na tabela,
     * referenciando esse relacionamento
     * */
    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;
    private Boolean assistida;


    /**
     * Construtores, Getters & Setters e HashCode & Equals da Entidade e de seus atributos
     * */

    public ProgressoAula() {}

    public ProgressoAula(Long id, Usuario usuario, Aula aula, Boolean assistida) {
        this.id = id;
        this.usuario = usuario;
        this.aula = aula;
        this.assistida = assistida;
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

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Boolean getAssistida() {
        return assistida;
    }

    public void setAssistida(Boolean assistida) {
        this.assistida = assistida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressoAula that = (ProgressoAula) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
