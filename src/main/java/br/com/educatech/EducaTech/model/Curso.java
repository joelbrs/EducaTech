package br.com.educatech.EducaTech.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Entidade Aula, referenciada pela tabela "tb_aula"
 * */
@Entity
@Table(name = "tb_curso")
public class Curso {

    /**
     * Atributos da entidade em questão, apresentando, inclusive, seus relacionamentos
     * com outras entidades do sistema
     * */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private BigDecimal cargaHoraria;

    @Column(columnDefinition = "TEXT")
    private String imagem;

    /**
     * Relacionamento "um-para-muitos" com a entidade de Aula, onde o mapeamento desse relacionamento foi realizado
     * na entidade Aula
     * */
    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
    private Set<Aula> aulas = new HashSet<>();

    /**
     * Relacionamento "um-para-muitos" com a entidade de Módulos, onde o mapeamento desse relacionamento foi realizado
     * na entidade Módulo
     * */
    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
    private Set<Modulo> modulos = new HashSet<>();

    /**
     * Relacionamento "um-para-muitos" com a entidade de ProgressoCurso, onde o mapeamento desse relacionamento foi realizado
     * na entidade ProgressoCurso
     * */
    @OneToMany(mappedBy = "curso")
    private Set<ProgressoCurso> progressoCurso = new HashSet<>();

    /**
     * Relacionamento "muitos-para-um" com a entidade Certificado, criando um atributo "certificado_id" na tabela,
     * referenciando esse relacionamento
     * */
    @ManyToOne
    @JoinColumn(name = "certificado_id")
    private Certificado modeloCertificado;


    /**
     * Construtores, Getters & Setters e HashCode & Equals da Entidade e de seus atributos
     * */

    public Curso() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(BigDecimal cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Set<Modulo> getModulos() {
        return Collections.unmodifiableSet(modulos);
    }

    public Set<Aula> getAulas() {
        return Collections.unmodifiableSet(aulas);
    }

    public Set<ProgressoCurso> getProgressoCurso() {
        return Collections.unmodifiableSet(progressoCurso);
    }

    public void setProgressoCurso(Set<ProgressoCurso> progressoCurso) {
        this.progressoCurso = progressoCurso;
    }

    public Certificado getModeloCertificado() {
        return modeloCertificado;
    }

    public void setModeloCertificado(Certificado modeloCertificado) {
        this.modeloCertificado = modeloCertificado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
