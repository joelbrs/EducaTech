package br.com.educatech.EducaTech.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Entidade Aula, referenciada pela tabela "tb_aula"
 * */
@Entity
@Table(name = "tb_aula")
public class Aula {

    /**
     * Atributos da entidade em questão, apresentando, inclusive, seus relacionamentos
     * com outras entidades do sistema
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Relacionamento "muitos-para-um" com a entidade Curso, criando um atributo "curso_id" na tabela,
     * referenciando esse relacionamento
     * */
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    /**
     * Relacionamento "muitos-para-um" com a entidade Módulo, criando um atributo "modulo_id" na tabela,
     * referenciando esse relacionamento
     * */
    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    private Integer ordem;
    private String titulo;
    private String descricao;
    private String video;

    /**
     * Relacionamento "um-para-muitos" com a entidade de ProgressoAula, onde o mapeamento desse relacionamento foi realizado
     * na entidade ProgressoAula
     * */
    @OneToMany(mappedBy = "aula")
    private Set<ProgressoAula> progressoAulas = new HashSet<>();


    /**
     * Construtores, Getters & Setters e HashCode & Equals da Entidade e de seus atributos
     * */

    public Aula() {}

    public Aula(Curso curso, Modulo modulo, String titulo, String descricao, Integer ordem, String video) {
        this.curso = curso;
        this.modulo = modulo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.ordem = ordem;
        this.video = video;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public Set<ProgressoAula> getProgressoAulas() {
        return Collections.unmodifiableSet(progressoAulas);
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
