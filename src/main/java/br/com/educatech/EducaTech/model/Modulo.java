package br.com.educatech.EducaTech.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



/**
 * Entidade Módulo, referenciada pela tabela "tb_modulo"
 * */
@Entity
@Table(name = "tb_modulo")
public class Modulo {

    /**
     * Atributos da entidade em questão, apresentando, inclusive, seus relacionamentos
     * com outras entidades do sistema
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Integer ordem;

    /**
     * Relacionamento "muitos-para-um" com a entidade Material, criando um atributo "material_id" na tabela,
     * referenciando esse relacionamento
     * */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id")
    private Material material;

    /**
     * Relacionamento "muitos-para-um" com a entidade Curso, criando um atributo "curso_id" na tabela,
     * referenciando esse relacionamento
     * */
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    /**
     * Relacionamento "um-para-muitos" com a entidade de Aula, onde o mapeamento desse relacionamento foi realizado
     * na entidade Aula
     * */
    @OneToMany(mappedBy = "modulo")
    private Set<Aula> aulas = new HashSet<>();


    /**
     * Construtores, Getters & Setters e HashCode & Equals da Entidade e de seus atributos
     * */

    public Modulo() {}

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

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public Curso getCurso() {
        return curso;
    }

    public Set<Aula> getAulas() {
        return Collections.unmodifiableSet(aulas);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modulo modulo = (Modulo) o;
        return Objects.equals(id, modulo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
